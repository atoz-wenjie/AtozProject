function deleteUser(uName,uid){
    Swal.fire({
      title: 'Are you sure to delete user "'+ uName +'"?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Delete',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      customClass:{
        cancelButton: 'order-1 right-gap',
        confirmButton: 'order-2',
      }
      }).then((result) => {
        if (result.isConfirmed) {
          window.location.href = "/deleteUser/"+ uid;
          
        } else if (result.isDenied) {

        }
      })
  }
  
  function confirmationPopupSubmitButton(method){
//	console.log("/"+method+"/"+id);
//	
	var finalHref = "/"+method;
	for (var i = 1; i < arguments.length; i++) {
//	    console.log(arguments[i])
	    finalHref += "/"+arguments[i];
	  }
  	console.log(finalHref)
    Swal.fire({
      title: 'Are you sure you want to Submit?',
//      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Success',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      customClass:{
        cancelButton: 'order-1 right-gap',
        confirmButton: 'order-2',
      }
      }).then((result) => {
        if (result.isConfirmed) {
          window.location.href = finalHref;
          
        } else if (result.isDenied) {

        }
      })
  }
  
