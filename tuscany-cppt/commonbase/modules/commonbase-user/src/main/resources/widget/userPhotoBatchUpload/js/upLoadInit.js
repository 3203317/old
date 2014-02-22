var swfu;
pageInitSWFUpload = function(limitNum,singleFileMaxSize,sessionId)
{
  swfu = new SWFUpload({
    // Backend settings
    upload_url: "/userPhotoBatchUpload/?sessionId="+sessionId,
    file_post_name: "resume_file",

    // Flash file settings
      file_size_limit : singleFileMaxSize + " MB",
      file_types : "*.jpg",     // or you could use something like: "*.doc;*.wpd;*.pdf",
      file_types_description : "jpg only",
      file_upload_limit : limitNum,
      file_queue_limit : limitNum,

      // Event handler settings
      swfupload_loaded_handler : swfUploadLoaded,

       file_dialog_start_handler: fileDialogStart,
      /*file_queued_handler : fileQueued,
      file_queue_error_handler : fileQueueError,*/
      file_dialog_complete_handler : fileDialogComplete,
      file_queued_handler : fileQueued,
      file_queue_error_handler : fileQueueError,

      swfupload_preload_handler : preLoad,
      swfupload_load_failed_handler : loadFailed,
      upload_start_handler : uploadStart, // I could do some client/JavaScript validation here, but I don't need to.
      upload_progress_handler : uploadProgress,
      upload_error_handler : uploadError,
      upload_success_handler : uploadSuccess,
      upload_complete_handler : uploadComplete,

      // Button Settings
      button_placeholder_id : "spanButtonPlaceholder",
      button_image_url : "images/upload.jpg",
      button_width : 61,
      button_height : 22,
      button_text_left_padding : 3,
      button_text_top_padding : 2,

      // Flash Settings
      flash_url : "swfupload/swfupload.swf",
      flash9_url : "swfupload/swfupload_fp9.swf",

      custom_settings : {
        progressTarget : "fsUploadProgress",
        cancelButtonId : "btnCancel",
        upload_successful : false
      },

      // Debug settings
      debug: false
  });
}