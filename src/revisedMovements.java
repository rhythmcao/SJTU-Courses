private void moveLeft{
	int row=blocks.length;
	int col=blocks[0].length;
	int i=0,j=0,k=0,score=0;
	boolean moveMade=false,succeed=false;
	for(i=0;i<row;++i){
		for (j = 1; j < col; j++) {
			if (blocks[i][j].getNum() == 0)
				continue; /* skip blanks */
			if (blocks[i][j].getNum() == blocks[i][h].getNum()) {
				changeBlock(blocks[i][h],blocks[i][h].getNum()<<1);
				score += blocks[i][h].getNum();
				/*
				 * merge with the previous same value: 
				 * <x>, <0>, ..., <x>, ... --> (<x> + <x>), <0>, ..., <0>, ...
				 */
				moveMade = true;
				changeBlock(blocks[i][j],0);
				if(blocks[i][h].getNum()==2048)
					succeed=true;
				h=h+1;
			} else {
				/*
				 * different value, so that the value x at h already has its final value
				 */
				if (blocks[i][h].getNum() == 0) {
					changeBlock(blocks[i][h], blocks[i][j].getNum());
					/*
					 * in this case, <0>, <0>, ..., <y>, ... --> <y>, <0>, ... <0>, ...
					 */
					changeBlock(blocks[i][j], 0);
				} else {
					h=h+1;
					changeBlock(blocks[i][h], blocks[i][j].getNum());
					/*
					 * in this case, <x>, <0>, ..., <y>, ... --> <x>, <y>, ..., <0>, ...
					 */
					if (h != j) {
						moveMade = true;
						changeBlock(blocks[i][j], 0);
					}
					/* if h == j, nothing is changed */
				}
			}
		}
	}
	score=(moveMade)?score:-1;
	if(score>=0){
		currentPoint += score;
		if(currentPoint > maxPoint) {
			maxPoint = currentPoint;
			isMaxPointChanged = true;
			setPoints(false, true, true, false);
		}else {
			setPoints(false, false, true, false);
		}
		if(!succeed)
			addBlock();
		else
			succeed();
	}
}

private void moveRight{
	int row=blocks.length;
	int col=blocks[0].length;
	int i=0,j=0,k=0,score=0;
	boolean moveMade=false,succeed=false;
	for(i=0;i<row;++i){
		for (j = 1; j < col; j++) {
			if (blocks[i][j].getNum() == 0)
				continue; /* skip blanks */
			if (blocks[i][j].getNum() == blocks[i][h].getNum()) {
				changeBlock(blocks[i][h],blocks[i][h].getNum()<<1);
				score += blocks[i][h].getNum();
				/*
				 * merge with the previous same value: 
				 * <x>, <0>, ..., <x>, ... --> (<x> + <x>), <0>, ..., <0>, ...
				 */
				moveMade = true;
				changeBlock(blocks[i][j],0);
				if(blocks[i][h].getNum()==2048)
					succeed=true;
				h=h+1;
			} else {
				/*
				 * different value, so that the value x at h already has its final value
				 */
				if (blocks[i][h].getNum() == 0) {
					changeBlock(blocks[i][h], blocks[i][j].getNum());
					/*
					 * in this case, <0>, <0>, ..., <y>, ... --> <y>, <0>, ... <0>, ...
					 */
					changeBlock(blocks[i][j], 0);
				} else {
					h=h+1;
					changeBlock(blocks[i][h], blocks[i][j].getNum());
					/*
					 * in this case, <x>, <0>, ..., <y>, ... --> <x>, <y>, ..., <0>, ...
					 */
					if (h != j) {
						moveMade = true;
						changeBlock(blocks[i][j], 0);
					}
					/* if h == j, nothing is changed */
				}
			}
		}
	}
	score=(moveMade)?score:-1;
	if(score>=0){
		currentPoint += score;
		if(currentPoint > maxPoint) {
			maxPoint = currentPoint;
			isMaxPointChanged = true;
			setPoints(false, true, true, false);
		}else {
			setPoints(false, false, true, false);
		}
		if(!succeed)
			addBlock();
		else
			succeed();
	}
}

private void moveUp{
	int row=blocks.length;
	int col=blocks[0].length;
	int i=0,j=0,k=0,score=0;
	boolean moveMade=false,succeed=false;
	for(i=0;i<row;++i){
		for (j = 1; j < col; j++) {
			if (blocks[i][j].getNum() == 0)
				continue; /* skip blanks */
			if (blocks[i][j].getNum() == blocks[i][h].getNum()) {
				changeBlock(blocks[i][h],blocks[i][h].getNum()<<1);
				score += blocks[i][h].getNum();
				/*
				 * merge with the previous same value: 
				 * <x>, <0>, ..., <x>, ... --> (<x> + <x>), <0>, ..., <0>, ...
				 */
				moveMade = true;
				changeBlock(blocks[i][j],0);
				if(blocks[i][h].getNum()==2048)
					succeed=true;
				h=h+1;
			} else {
				/*
				 * different value, so that the value x at h already has its final value
				 */
				if (blocks[i][h].getNum() == 0) {
					changeBlock(blocks[i][h], blocks[i][j].getNum());
					/*
					 * in this case, <0>, <0>, ..., <y>, ... --> <y>, <0>, ... <0>, ...
					 */
					changeBlock(blocks[i][j], 0);
				} else {
					h=h+1;
					changeBlock(blocks[i][h], blocks[i][j].getNum());
					/*
					 * in this case, <x>, <0>, ..., <y>, ... --> <x>, <y>, ..., <0>, ...
					 */
					if (h != j) {
						moveMade = true;
						changeBlock(blocks[i][j], 0);
					}
					/* if h == j, nothing is changed */
				}
			}
		}
	}
	score=(moveMade)?score:-1;
	if(score>=0){
		currentPoint += score;
		if(currentPoint > maxPoint) {
			maxPoint = currentPoint;
			isMaxPointChanged = true;
			setPoints(false, true, true, false);
		}else {
			setPoints(false, false, true, false);
		}
		if(!succeed)
			addBlock();
		else
			succeed();
	}
}

private void moveDown{
	int row=blocks.length;
	int col=blocks[0].length;
	int i=0,j=0,k=0,score=0;
	boolean moveMade=false,succeed=false;
	for(i=0;i<row;++i){
		for (j = 1; j < col; j++) {
			if (blocks[i][j].getNum() == 0)
				continue; /* skip blanks */
			if (blocks[i][j].getNum() == blocks[i][h].getNum()) {
				changeBlock(blocks[i][h],blocks[i][h].getNum()<<1);
				score += blocks[i][h].getNum();
				/*
				 * merge with the previous same value: 
				 * <x>, <0>, ..., <x>, ... --> (<x> + <x>), <0>, ..., <0>, ...
				 */
				moveMade = true;
				changeBlock(blocks[i][j],0);
				if(blocks[i][h].getNum()==2048)
					succeed=true;
				h=h+1;
			} else {
				/*
				 * different value, so that the value x at h already has its final value
				 */
				if (blocks[i][h].getNum() == 0) {
					changeBlock(blocks[i][h], blocks[i][j].getNum());
					/*
					 * in this case, <0>, <0>, ..., <y>, ... --> <y>, <0>, ... <0>, ...
					 */
					changeBlock(blocks[i][j], 0);
				} else {
					h=h+1;
					changeBlock(blocks[i][h], blocks[i][j].getNum());
					/*
					 * in this case, <x>, <0>, ..., <y>, ... --> <x>, <y>, ..., <0>, ...
					 */
					if (h != j) {
						moveMade = true;
						changeBlock(blocks[i][j], 0);
					}
					/* if h == j, nothing is changed */
				}
			}
		}
	}
	score=(moveMade)?score:-1;
	if(score>=0){
		currentPoint += score;
		if(currentPoint > maxPoint) {
			maxPoint = currentPoint;
			isMaxPointChanged = true;
			setPoints(false, true, true, false);
		}else {
			setPoints(false, false, true, false);
		}
		if(!succeed)
			addBlock();
		else
			succeed();
	}
}