function fileChange(target,id) {     
        var fileSize = 0;          
        if (-[1,]&& !target.files) {      
              var filePath = target.value;     
              var fileSystem = new ActiveXObject("Scripting.FileSystemObject");  
                
              if(!fileSystem.FileExists(filePath)){  
                 alert("附件不存在，请重新输入！");  
                 var file=document.getElementById(id);   
                 file.outerHTML=file.outerHTML;  
                 return;  
              }  
              var file = fileSystem.GetFile (filePath);  
              fileSize = file.Size;     
        } else {     
              fileSize = target.files[0].size;   
        }    
          
        var size = fileSize /1024/1024;
       // alert(size);
        if(size>6*1024){   
             alert("附件大小不能大于2G！");   
             var file=document.getElementById(id);   
             file.outerHTML=file.outerHTML  
        }    
        if(size<=0){  
            alert("附件无效，请重新选择文件！");   
            var file=document.getElementById(id);   
             file.outerHTML=file.outerHTML  
        } 
        fileRealSize=fileSize;
     }   

function  getFileSize(){
	var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
}
