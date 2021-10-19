package kr.hs.entrydsm.application.usecase.exception;

import kr.hs.entrydsm.common.context.exception.ErrorCode;
import kr.hs.entrydsm.common.context.exception.MunchkinException;

public class ImageNotFoundException extends MunchkinException {

	public ImageNotFoundException() {
		super(ErrorCode.IMAGE_NOT_FOUND);
	}

}
