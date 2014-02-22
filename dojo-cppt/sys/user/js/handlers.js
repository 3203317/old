/*
  A simple class for displaying file information and progress
  Note: This is a demonstration only and not part of SWFUpload.
  Note: Some have had problems adapting this class in IE7. It may not be suitable for your application.
*/

// Constructor
// file is a SWFUpload file object
// targetID is the HTML element id attribute that the FileProgress HTML structure will be added to.
// Instantiating a new FileProgress object with an existing file will reuse/update the existing DOM elements

var uploadFileNum = 0;
var successUpload = 0;
var errorUpload = 0;
function FileProgress(file, targetID) {
  this.fileProgressID = file.id;

  this.opacity = 100;
  this.height = 0;


  this.fileProgressWrapper = document.getElementById(this.fileProgressID);
  if (!this.fileProgressWrapper) {
    this.fileProgressWrapper = document.createElement("div");
    this.fileProgressWrapper.className = "progressWrapper";
    this.fileProgressWrapper.id = this.fileProgressID;

    this.fileProgressElement = document.createElement("div");
    this.fileProgressElement.className = "progressContainer";

    var progressCancel = document.createElement("a");
    progressCancel.className = "progressCancel";
    progressCancel.href = "#";
    progressCancel.style.visibility = "hidden";
    progressCancel.appendChild(document.createTextNode(" "));

    var progressText = document.createElement("div");
    progressText.className = "progressName";
    progressText.appendChild(document.createTextNode(file.name));

    var progressBar = document.createElement("div");
    progressBar.className = "progressBarInProgress";

    var progressStatus = document.createElement("div");
    progressStatus.className = "progressBarStatus";
    progressStatus.innerHTML = "&nbsp;";

    this.fileProgressElement.appendChild(progressCancel);
    this.fileProgressElement.appendChild(progressText);
    this.fileProgressElement.appendChild(progressStatus);
    this.fileProgressElement.appendChild(progressBar);

    this.fileProgressWrapper.appendChild(this.fileProgressElement);

    document.getElementById(targetID).appendChild(this.fileProgressWrapper);
  } else {
    this.fileProgressElement = this.fileProgressWrapper.firstChild;
    this.reset();
  }

  this.height = this.fileProgressWrapper.offsetHeight;
  this.setTimer(null);


}

FileProgress.prototype.setTimer = function (timer) {
  this.fileProgressElement["FP_TIMER"] = timer;
};
FileProgress.prototype.getTimer = function (timer) {
  return this.fileProgressElement["FP_TIMER"] || null;
};

FileProgress.prototype.reset = function () {
  this.fileProgressElement.className = "progressContainer";

  this.fileProgressElement.childNodes[2].innerHTML = "&nbsp;";
  this.fileProgressElement.childNodes[2].className = "progressBarStatus";

  this.fileProgressElement.childNodes[3].className = "progressBarInProgress";
  this.fileProgressElement.childNodes[3].style.width = "0%";

  this.appear();
};

FileProgress.prototype.setProgress = function (percentage) {
  this.fileProgressElement.className = "progressContainer green";
  this.fileProgressElement.childNodes[3].className = "progressBarInProgress";
  this.fileProgressElement.childNodes[3].style.width = percentage + "%";

  this.appear();
};
FileProgress.prototype.setComplete = function () {
  this.fileProgressElement.className = "progressContainer blue";
  this.fileProgressElement.childNodes[3].className = "progressBarComplete";
  this.fileProgressElement.childNodes[3].style.width = "";

  var oSelf = this;
  this.setTimer(setTimeout(function () {
    oSelf.disappear();
  }, 10000));
};
FileProgress.prototype.setError = function () {
  this.fileProgressElement.className = "progressContainer red";
  this.fileProgressElement.childNodes[3].className = "progressBarError";
  this.fileProgressElement.childNodes[3].style.width = "";

  var oSelf = this;
//  this.setTimer(setTimeout(function () {
//    oSelf.disappear();
//  }, 5000));
};
FileProgress.prototype.setCancelled = function () {
  this.fileProgressElement.className = "progressContainer";
  this.fileProgressElement.childNodes[3].className = "progressBarError";
  this.fileProgressElement.childNodes[3].style.width = "";

  var oSelf = this;
  this.setTimer(setTimeout(function () {
    oSelf.disappear();
  }, 2000));
  reduceFile_uploadFileNum();
};
FileProgress.prototype.setStatus = function (status) {
  this.fileProgressElement.childNodes[2].innerHTML = status;
};

// Show/Hide the cancel button
FileProgress.prototype.toggleCancel = function (show, swfUploadInstance) {
  this.fileProgressElement.childNodes[0].style.visibility = show ? "visible" : "hidden";
  if (swfUploadInstance) {
    var fileID = this.fileProgressID;
    this.fileProgressElement.childNodes[0].onclick = function () {
      swfUploadInstance.cancelUpload(fileID);
      return false;
    };
  }
};

FileProgress.prototype.appear = function () {
  if (this.getTimer() !== null) {
    clearTimeout(this.getTimer());
    this.setTimer(null);
  }

  if (this.fileProgressWrapper.filters) {
    try {
      this.fileProgressWrapper.filters.item("DXImageTransform.Microsoft.Alpha").opacity = 100;
    } catch (e) {
      // If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
      this.fileProgressWrapper.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=100)";
    }
  } else {
    this.fileProgressWrapper.style.opacity = 1;
  }

  this.fileProgressWrapper.style.height = "";

  this.height = this.fileProgressWrapper.offsetHeight;
  this.opacity = 100;
  this.fileProgressWrapper.style.display = "";

};

// Fades out and clips away the FileProgress box.
FileProgress.prototype.disappear = function () {

  var reduceOpacityBy = 15;
  var reduceHeightBy = 4;
  var rate = 30;	// 15 fps

  if (this.opacity > 0) {
    this.opacity -= reduceOpacityBy;
    if (this.opacity < 0) {
      this.opacity = 0;
    }

    if (this.fileProgressWrapper.filters) {
      try {
        this.fileProgressWrapper.filters.item("DXImageTransform.Microsoft.Alpha").opacity = this.opacity;
      } catch (e) {
        // If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
        this.fileProgressWrapper.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=" + this.opacity + ")";
      }
    } else {
      this.fileProgressWrapper.style.opacity = this.opacity / 100;
    }
  }

  if (this.height > 0) {
    this.height -= reduceHeightBy;
    if (this.height < 0) {
      this.height = 0;
    }

    this.fileProgressWrapper.style.height = this.height + "px";
  }

  if (this.height > 0 || this.opacity > 0) {
    var oSelf = this;
    this.setTimer(setTimeout(function () {
      oSelf.disappear();
    }, rate));
  } else {
    this.fileProgressWrapper.style.display = "none";
    this.setTimer(null);
  }
};

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
  var btnSubmit = document.getElementById("btnSubmit");
  btnSubmit.onclick = doSubmit;
}
function addFile_errorUpload()
{
  errorUpload++;
  document.getElementById("fileUploadError").innerHTML = "上传失败"+errorUpload+"个文件。";
}
function addFile_successUpload()
{
  successUpload++;
  document.getElementById("fileUploadResult").innerHTML = "成功上传了"+successUpload+"个文件。";
}
function reduceFile_successUpload()
{
  successUpload--;
  document.getElementById("fileUploadResult").innerHTML = "成功上传了"+successUpload+"个文件。";
}
function addFile_uploadFileNum()
{
  uploadFileNum++;
  document.getElementById("fileUploaddesc").innerHTML = "成功选择了"+uploadFileNum+"个文件。";
}
function reduceFile_uploadFileNum()
{
  uploadFileNum--;
  document.getElementById("fileUploaddesc").innerHTML = "成功选择了"+uploadFileNum+"个文件。";
}

function cleanFileUploadStatus()
{
  //errorUpload不为0说明上传过文件了，有失败
  //successUpload不为0说明上传文件成功的个数
  //满足这样的情况下点击浏览需要清空所有的原来记录的消息
  
  if(errorUpload != 0 || successUpload !=0)
  {
    swfu.cleanUp();
    errorUpload = 0;
    successUpload = 0;
    uploadFileNum = 0;
    document.getElementById("fsUploadProgress").innerHTML="";
    document.getElementById("fileUploadResult").innerHTML="";
    document.getElementById("fileUploaddesc").innerHTML = "";
    document.getElementById("fileUploadError").innerHTML = "";
  }
}
// Called by the submit button to start the upload
function doSubmit(e) {

  e = e || window.event;
  if (e.stopPropagation) {
    e.stopPropagation();
  }
  e.cancelBubble = true;

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
    //console.log(ex.message);
  }
  return false;
}

 // Called by the queue complete handler to submit the form
function uploadDone() {
  try {
    document.forms[0].submit();
  } catch (ex) {
    alert("Error submitting form");
  }
}

function fileDialogStart() {
  cleanFileUploadStatus();
  this.cancelUpload();
}



function fileQueueError(file, errorCode, message)  {
  try {
    reduceFile_uploadFileNum();
    if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
      alert("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));
      return;
    }

    var progress = new FileProgress(file, this.customSettings.progressTarget);
    progress.setError();
    progress.toggleCancel(false);

    // Handle this error separately because we don't want to create a FileProgress element for it.
    switch (errorCode) {
    case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
      alert("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));
      return;
    case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
      alert("The file you selected is too big.");
      this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
      return;
    case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
      alert("The file you selected is empty.  Please select another file.");
      this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
      return;
    case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
      alert("The file you choose is not an allowed file type.");
      this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
      return;
    default:
      alert("An error occurred in the upload. Try again later.");
      this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
      return;
    }
  } catch (e) {
  }
}

function fileQueued(file) {
  try {
     addFile_uploadFileNum();
     var progress = new FileProgress(file, this.customSettings.progressTarget);
     progress.setStatus("等待上传...");
     progress.toggleCancel(true,this);

  } catch (e) {
  }

}
function fileDialogComplete(numFilesSelected, numFilesQueued) {
  //validateForm();
  console.log(numFilesSelected);
  console.log(numFilesQueued);
}

function uploadProgress(file, bytesLoaded, bytesTotal) {

  try {
    var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);

    //file.id = "singlefile";	// This makes it so FileProgress only makes a single UI element, instead of one for each file
    var progress = new FileProgress(file, this.customSettings.progressTarget);
    progress.setProgress(percent);
    progress.setStatus("上传中...");
  } catch (e) {
  }
}

function uploadSuccess(file, serverData) {
  try {
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
    addFile_successUpload();
    var progress = new FileProgress(file, this.customSettings.progressTarget);
    //console.log(file.name);
    progress.setComplete();
    progress.setStatus("上传完成.");
    progress.toggleCancel(false);

  } catch (e) {
  }
}
function uploadStart(file) {
  try {
    /* I don't want to do any file validation or anything,  I'll just update the UI and
    return true to indicate that the upload should start.
    It's important to update the UI here because in Linux no uploadProgress events are called. The best
    we can do is say we are uploading.
     */
    var progress = new FileProgress(file, this.customSettings.progressTarget);
    progress.setStatus("上传中...");
    progress.toggleCancel(true);
  }
  catch (ex) {}

  return true;
}
function uploadComplete(file) {
  try
  {
    var noFile = (this.getStats().files_queued === 0);
    if (noFile)
    {
      //console.log("无文件");
      return;
    }
    else
    {
      this.startUpload();
    }
  }
  catch (e)
  {
    //console.log(e.message);
  }
}

function uploadError(file, errorCode, message) {
  if(errorCode != -280)
  {
    addFile_errorUpload();
  }
  try {
     var progress = new FileProgress(file, this.customSettings.progressTarget);
     progress.setError();
     progress.toggleCancel(false);
     if(message == 500)
     {
       progress.setStatus("session 过期");
       return;
     }
     if(message == 490)
     {
       progress.setStatus("无效用户");
       return;
     }
     switch (errorCode) {
     case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
     progress.setStatus("Upload Error: " + message);
     this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
     break;
     case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
     progress.setStatus("Upload Failed.");
     this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
     break;
     case SWFUpload.UPLOAD_ERROR.IO_ERROR:
     progress.setStatus("Server (IO) Error");
     this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
     break;
     case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
     progress.setStatus("Security Error");
     this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
     break;
     case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
     progress.setStatus("Upload limit exceeded.");
     this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
     break;
     case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
     progress.setStatus("Failed Validation. Upload skipped.");
     this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
     break;
     case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
     progress.setStatus("Cancelled");
     progress.setCancelled();
     break;
     case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
     progress.setStatus("Stopped");
     break;
     case SWFUpload.UPLOAD_ERROR.INVAILD_SESSION:
     progress.setStatus("session 过期");
     break;
     case SWFUpload.UPLOAD_ERROR.NONE_USER:
     progress.setStatus("上传照片没有对应的用户");
     break;
     case SWFUpload.UPLOAD_ERROR.UNKNOWN_ERROR:
     progress.setStatus("上传失败，未知异常");
     break;
     default:
     progress.setStatus("Unhandled Error: " + errorCode);
     this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
     break;
    }
  } catch (ex) {
  }
}


