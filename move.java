	private void moveLeftOrRight(Direction direction) {
		int result = 0;
		int jBegin = 1;
		int jEnd = 4;
		if(direction == Direction.Right) {
			jBegin = 0;
			jEnd = 3;
		}
		//先按列扫描
		for(int j = jBegin;j < jEnd;j ++) {
			for(int i = 0;i < 4;i ++) {
				int vAxis = (direction == Direction.Right) ? 2 - j : j;
				Panel panel = blocks[i][vAxis];
				//当前块有数字
				if(panel.getNum() != 0) {
					Point nextPosition = getNextBlock(direction, i, vAxis);
					Panel next = blocks[nextPosition.x][nextPosition.y];
					//如果左面或右面没有方块，直接交换
					if(next.getNum() == 0) {
						changeBlock(next, panel.getNum());
						changeBlock(panel, 0);
					}else if(next.getNum() == panel.getNum()) {
						//如果下一个位置的数字和当前的相等，那么相加并且当前的清零
						result = next.getNum() << 1;
						currentPoint += result;
						if(currentPoint > maxPoint) {
							maxPoint = currentPoint;
							isMaxPointChanged = true;
							setPoints(false, true, true, false);
						}else {
							setPoints(false, false, true, false);
						}
						changeBlock(next, result);
						changeBlock(panel, 0);
						// determine whether 2048 is obtained
						if(result == 2048) {
							succeed();
						}
					}else {
						//不相等，直接移动到下一个的左方或右方
						int nextPos = (direction == Direction.Right) ? nextPosition.y - 1 : nextPosition.y + 1;
						//如果下一个位置不是当前位置
						if(i != nextPosition.x || vAxis != nextPos) {
							changeBlock(blocks[nextPosition.x][nextPos], panel.getNum());
							changeBlock(panel, 0);
						}
					}
				}
			}
		}
		if(result < 2048) {
			addBlock();
		}
	}

	/**
	 * 上下移
	 */
	private void moveUpOrDown(Direction direction) {
		int result = 0;
		int iBegin = 0;
		int iEnd = 3;
		if(direction == Direction.Up) {
			iBegin = 1;
			iEnd = 4;
		}
		for(int i = iBegin;i < iEnd;i ++) {
			for(int j = 0;j < 4;j ++) {
				int hAxis = (direction == Direction.Down) ? 2 - i : i;
				Panel panel = blocks[hAxis][j];
				//当前块有数字
				if(panel.getNum() != 0) {
					Point nextPosition = getNextBlock(direction, hAxis, j);
					Panel next = blocks[nextPosition.x][nextPosition.y];
					//如果下面没有方块，直接交换
					if(next.getNum() == 0) {
						changeBlock(next, panel.getNum());
						changeBlock(panel, 0);
					}else if(next.getNum() == panel.getNum()) {
						//如果下一个位置的数字和当前的相等，那么相加并且当前的清零
						result = next.getNum() << 1;
						currentPoint += result;
						if(currentPoint > maxPoint) {
							maxPoint = currentPoint;
							isMaxPointChanged = true;
							setPoints(false, true, true, false);
						}else {
							setPoints(false, false, true, false);
						}
						changeBlock(next, result);
						changeBlock(panel, 0);
						//判断是否达到2048
						if(result == 2048) {
							succeed();
						}
					}else {
						//不相等，直接移动到下一个的上方或下方
						int nextPos = (direction == Direction.Down) ? nextPosition.x - 1 : nextPosition.x + 1;
						//如果下一个位置不是当前位置
						if(hAxis != nextPos || j != nextPosition.y) {
							changeBlock(blocks[nextPos][nextPosition.y], panel.getNum());
							changeBlock(panel, 0);
						}
					}
				}
			}
		}
		if(result < 2048) {
			addBlock();
		}
	}

	/**
	 * 获取下一个移动到的位置
	 */
	private Point getNextBlock(Direction direction, int i, int j) {
		switch (direction) {
		case Down:
			for(int m = i + 1;m < 4;m ++) {
				Panel panel = blocks[m][j];
				//找到不为零的立即返回或者是最后一行
				if(panel.getNum() != 0 || m == 3) {
					return new Point(m, j);
				}
			}
			break;
		case Up:
			for(int m = i - 1;m >= 0;m --) {
				Panel panel = blocks[m][j];
				//找到不为零的立即返回或者是第一行
				if(panel.getNum() != 0 || m == 0) {
					return new Point(m, j);
				}
			}
			break;
		case Left:
			for(int m = j - 1;m >= 0;m --) {
				Panel panel = blocks[i][m];
				//找到不为零的立即返回或者是第一列
				if(panel.getNum() != 0 || m == 0) {
					return new Point(i, m);
				}
			}
			break;
		case Right:
			for(int m = j + 1;m < 4;m ++) {
				Panel panel = blocks[i][m];
				//找到不为零的立即返回或者是最后一列
				if(panel.getNum() != 0 || m == 3) {
					return new Point(i, m);
				}
			}
		default:
			break;
		}
		return null;
	}