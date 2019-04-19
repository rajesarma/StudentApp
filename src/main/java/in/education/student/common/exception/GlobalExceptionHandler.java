package in.education.student.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
//@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ModelAndView handleInvalidHeaderFieldException(InvalidHeaderFieldException ex) {
		ModelAndView mav = new ModelAndView("error");	// shows 404.jsp
		mav.addObject("message", ex.getMessage());

		return mav;
	}

	@ExceptionHandler
	public ModelAndView handleNullPointerException(NullPointerException ex) {
		ModelAndView mav = new ModelAndView("error");	// shows 404.jsp
		mav.addObject("message", ex.getMessage());
//		System.out.println(ex.getMessage());

		return mav;
	}

	/*@ExceptionHandler
	public ResponseEntity<String> handleInvalidHeaderFieldException1(InvalidHeaderFieldException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
	}*/

	@ExceptionHandler
	public ModelAndView handleException(Exception ex) {
		ModelAndView mav = new ModelAndView("error");	// shows 404.jsp

		System.out.println(ex.getMessage());
		mav.addObject("message", ex.getMessage());

		return mav;
	}
}
