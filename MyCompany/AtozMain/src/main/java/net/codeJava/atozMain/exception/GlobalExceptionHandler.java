package net.codeJava.atozMain.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import net.codeJava.atozService.AppExceptionLogService;

@ControllerAdvice
public class GlobalExceptionHandler{

  // @RequestHandler methods
  
  // Exception handling methods
  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
  
  @Autowired
  public AppExceptionLogService appExceptionLogService;
  
  // Convert a predefined exception to an HTTP Status code
  @ResponseStatus(value=HttpStatus.CONFLICT,
                  reason="Data integrity violation")  // 409
  @ExceptionHandler(DataIntegrityViolationException.class)
  public void conflict() {
    // Nothing to do
  }
  
  // Specify name of a specific view that will be used to display the error:
//  @ExceptionHandler({SQLException.class,DataAccessException.class})
//  public String databaseError() {
    // Nothing to do.  Returns the logical view name of an error page, passed
    // to the view-resolver(s) in usual way.
    // Note that the exception is NOT available to this view (it is not added
    // to the model) but see "Extending ExceptionHandlerExceptionResolver"
    // below.
//    return "databaseError";
//  }

  // Total control - setup a model and return the view name yourself. Or
  // consider subclassing ExceptionHandlerExceptionResolver (see below).
  @ExceptionHandler(Exception.class)
  public ModelAndView handleError(HttpServletRequest req, Exception ex) throws SerialException, SQLException {
    logger.info("RequestException: " + req.getRequestURL() + " raised " + ex);

    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", ex);
    mav.addObject("url", req.getRequestURL());
    mav.setViewName("error");
    
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userName = "";
	if (principal instanceof UserDetails) {
		userName = ((UserDetails)principal).getUsername();
	} else {
	   userName = principal.toString();
	}
	
    appExceptionLogService.saveLog(ex.getMessage(), req.getRequestURL().toString(),userName);
    return mav;
  }
}
