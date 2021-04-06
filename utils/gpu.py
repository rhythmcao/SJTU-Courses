#coding=utf8
import os, sys, math
# special case in our remote server, just ignore
if '/cm/local/apps/cuda/libs/current/pynvml' in sys.path:
    sys.path.remove('/cm/local/apps/cuda/libs/current/pynvml')
import gpustat
import torch

def get_gpu_compute_rest(gpu_stats_list, gpu_id_list):
    device_compute_rest = {}
    for gpu_stat in gpu_stats_list:
        if gpu_stat['index'] not in gpu_id_list:
            continue
        memory_used = gpu_stat['memory.used']
        memory_total = gpu_stat['memory.total']
        memory_rest = 1 - float(memory_used)/memory_total
        utilization_gpu = gpu_stat['utilization.gpu']
        utilization_gpu_rest = 1 - float(utilization_gpu)/100
        device_compute_rest[gpu_stat['index']] = (utilization_gpu_rest, memory_rest)
    print("GPU device state {idx:(compute_rest, memory_rest)} : " , device_compute_rest)
    return device_compute_rest

def auto_select_gpu(assigned_gpu_id=None, ngpus=1):
    """
        assigned_gpu_id is the main deviceId
        ngpus denote the total number of gpus
    """
    gpu_id_list = os.getenv('CUDA_VISIBLE_DEVICES')
    gpu_stats_list = gpustat.new_query()
    if gpu_id_list == None:
        gpu_id_list = [g['index'] for g in gpu_stats_list]
    else:
        gpu_id_list = [int(value.strip()) for value in gpu_id_list.split(',')]

    device_compute_rest = get_gpu_compute_rest(gpu_stats_list, gpu_id_list)
    device_first_level, device_second_level, device_third_level = {}, {}, {}
    for i in device_compute_rest:
        computeRestRate, memRestRate = device_compute_rest[i]
        if memRestRate > 0.5 and computeRestRate > 0.5:
            device_first_level[i] = math.sqrt(memRestRate * computeRestRate)
        elif memRestRate > 0.3 and computeRestRate > 0.3:
            device_second_level[i] = math.sqrt(memRestRate * computeRestRate)
        else:
            device_third_level[i] = math.sqrt(memRestRate * computeRestRate)
    best, valid_gpus = -1, []
    if assigned_gpu_id:
        best = assigned_gpu_id
        if best not in gpu_id_list:
            best = -1
            print("WARNING: Manually selected main gpu index is out of range! We will use autoselected gpu!")
        
    def sort_candidate_gpus(devices):
        if len(devices) == 0:
            return []
        else:
            sort_list, _ = list(zip(*sorted(list(devices.items()), key=lambda x: x[1])))
            return list(sort_list)

    sort_first_list = sort_candidate_gpus(device_first_level)
    sort_second_list = sort_candidate_gpus(device_second_level)
    sort_third_list = sort_candidate_gpus(device_third_level)
    valid_gpus = sort_first_list + sort_second_list + sort_third_list
    if len(valid_gpus) < ngpus:
        raise ValueError('[Error]: not enough available gpus')
    valid_gpus = valid_gpus[:ngpus]
    if best == -1:
        best = valid_gpus[0]
    if best not in valid_gpus:
        valid_gpus[-1] = best
    best = gpu_id_list.index(best)
    gpu_name = gpu_stats_list[best]['name']
    valid_gpus = [gpu_id_list.index(each) for each in valid_gpus]
    return best, gpu_name, valid_gpus

def set_torch_device(deviceId=0, ngpus=1):
    # deviceId is used to select main gpu, ngpu is total number of gpus required
    assert torch.cuda.device_count() >= ngpus
    if deviceId >= 0:
        if deviceId > 0:
            deviceId, gpu_name, deviceIdList = auto_select_gpu(assigned_gpu_id=deviceId - 1, ngpus=ngpus)
        elif deviceId == 0:
            deviceId, gpu_name, deviceIdList = auto_select_gpu(ngpus=ngpus)
        print("Valid GPU list: %s ; GPU %d (%s) is auto selected." % (deviceIdList, deviceId, gpu_name))
        torch.cuda.set_device(deviceId)
        device = torch.device("cuda")
        # os.environ['CUDA_LAUNCH_BLOCKING'] = "1" # used when debug
    else:
        print("CPU is used.")
        device = torch.device("cpu")
        deviceIdList = []
    return device, deviceIdList

