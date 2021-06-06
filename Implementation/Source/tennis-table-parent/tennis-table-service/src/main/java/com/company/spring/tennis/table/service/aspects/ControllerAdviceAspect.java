package com.company.spring.tennis.table.service.aspects;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.company.spring.tennis.table.service.exceptions.ServiceException;
import com.company.spring.tennis.table.service.utilities.Constants;
import com.company.spring.tennis.table.service.utilities.RejectionReason;
import com.company.spring.tennis.table.service.utilities.ResponseStatusCodesAndMessages;
import com.company.spring.tennis.table.service.utilities.ServiceResult;

@ControllerAdvice
@Order(0)
public class ControllerAdviceAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ServiceResult handleServiceException(final ServiceException serviceException) {

		logger.info("handleServiceException {} ", serviceException);

		return constructErrorResponseObject(serviceException.getRejectionReason(), serviceException);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ServiceResult handleValidationExceptions(MethodArgumentNotValidException ex) {
		logger.info("handleValidationExceptions {} ", ex);

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ServiceResult(ResponseStatusCodesAndMessages.BAD_REQUEST_CODE, Constants.FAILED, Constants.FAILED,
				errors);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ServiceResult handleException(final Exception internalServerException) {

		logger.info("handleException {} :" + internalServerException);

		return constructErrorResponseObject(null, internalServerException);
	}

	public ServiceResult constructErrorResponseObject(RejectionReason rejectionReason, Exception e) {

		logger.info("constructErrorResponseObject {} :" + rejectionReason, e);

		if (rejectionReason == null) {
			rejectionReason = new RejectionReason();
			rejectionReason.setCode(99);
			rejectionReason.setReason(ResponseStatusCodesAndMessages.UNEXPECTED_RESULT);
		}

		return new ServiceResult(ResponseStatusCodesAndMessages.FAILED_CODE, Constants.FAILED, Constants.FAILED,
				rejectionReason);
	}

}
