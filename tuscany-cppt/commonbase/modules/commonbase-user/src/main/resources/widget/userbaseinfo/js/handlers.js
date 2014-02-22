
var formChecker = null;
function preLoad() {
  if (!this.support.loading) {
    alert("You need the Flash Player 9.028 or above to use SWFUpload.");
    return false;
  }
}
function loadFailed() {
  alert("Something went wrong while loading SWFUpload. If this were a real application we'd clean up and then give you an alternative");
}
function swfUploadLoaded() {
}

// Called by the submit button to start the upload
function doSubmit(e) {

  try {
    if(swfu.getFile() != null)
    {
      swfu.startUpload();
    }
    else
    {
      alert("没有可以上传的文件！");
    }
  } catch (ex) {
    console.log(ex.message);
  }
  return false;
}

 // Called by the queue complete handler to submit the form
function uploadDone() {
  try {
    document.getElementById('modifySingleUserPhoto').submit();
  } catch (ex) {
    alert("Error submitting form");
  }
}

function fileDialogStart() {
  var txtFileName = document.getElementById("singleFile");
  txtFileName.value = "";
  this.cancelUpload();
}



function fileQueueError(file, errorCode, message)  {
  try {
    if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
      alert("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));
      return;
    }
    // Handle this error separately because we don't want to create a FileProgress element for it.
    switch (errorCode) {
    case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
      alert("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));
      return;
    case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
      alert("The file you selected is too big.");
      return;
    case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
      alert("The file you selected is empty.  Please select another file.");
      return;
    case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
      alert("The file you choose is not an allowed file type.");
      return;
    default:
      alert("An error occurred in the upload. Try again later.");
      return;
    }
  } catch (e) {
  }
}

function fileQueued(file) {
  try {
    var txtFileName = document.getElementById("singleFile");
    txtFileName.value = file.name;
  } catch (e) {
  }

}
function fileDialogComplete(numFilesSelected, numFilesQueued) {
  //validateForm();
  doSubmit();
}

function uploadProgress(file, bytesLoaded, bytesTotal) {

  try {
  } catch (e) {
  }
}

function uploadSuccess(file, serverData) {
  try {
  if(serverData.indexOf("userPhotoID=") >=0)
    {
    swfUploadSuccess(serverData);
    }
    
    if(serverData.indexOf("-500") >=0)
    {
      swfu.uploadError(file,-500,"");
      return;
    }
    if(serverData.indexOf("-490") >=0)
    {
      swfu.uploadError(file,-490,"");
      return;
    }
    if(serverData.indexOf("NullPointerException") >=0)
    {
      swfu.uploadError(file,-480,"");
      return;
    }
    document.getElementById('submitSingleUserPhotoDIV').style.display = "none";
    document.getElementById('modifyPhotoDoor').style.display = "";
    document.getElementById("singleFile").value = "";
    swfu.cleanUp();
    alert("上传成功");
  } catch (e) {
    console.log(e.message);
  }
}
function uploadStart(file) {
}
function uploadComplete(file) {
}

function uploadError(file, errorCode, message) {
  try {
     if(message == 500)
     {
       alert("session 过期");
       return;
     }
     if(message == 490)
     {
       alert("无效用户");
       return;
     }
     if (errorCode === SWFUpload.UPLOAD_ERROR.FILE_CANCELLED) {
      // Don't show cancelled error boxes
      return;
      } 
     switch (errorCode) {
     case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
     alert("Upload Error: " + message);
     break;
     case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
     alert("Upload Failed.");
     break;
     case SWFUpload.UPLOAD_ERROR.IO_ERROR:
     alert("Server (IO) Error");
     break;
     case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
     alert("Security Error");
     break;
     case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
     alert("Upload limit exceeded.");
     break;
     case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
     alert("Failed Validation. Upload skipped.");
     break;
     case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
     //console.log("Cancelled");用户终止上传，此处打开可用于日志记录，此处进行了关闭
     break;
     case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
     alert("Stopped");
     break;
     case SWFUpload.UPLOAD_ERROR.INVAILD_SESSION:
     alert("session 过期");
     break;
     case SWFUpload.UPLOAD_ERROR.NONE_USER:
     alert("上传照片没有对应的用户");
     break;
     case SWFUpload.UPLOAD_ERROR.UNKNOWN_ERROR:
     alert("上传失败，未知异常");
     break;
     default:
     alert("Unhandled Error: " + errorCode);
     break;
    }
  } catch (ex) {
  }
}


