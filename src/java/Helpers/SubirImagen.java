/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.io.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author KarenLunaH
 */
public class SubirImagen {
    
    private final int limit_max_size=102400000;
    private final String limit_type_file = "gif|jpg|png|jpeg";
    private final String path_to = "resources/imagenes";
    
    public String processUpload(Part fileUpload) throws Exception{
        String fileSaveData=null;
        try{
            if(fileUpload.getSize()>0){
                
                String submittedFileName = this.getFileName(fileUpload); 
                if(checkFileType(submittedFileName)){
                    
                    if(fileUpload.getSize() > this.limit_max_size){
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(""));
                    }else{
                        
                        String currentFileName =  submittedFileName;
                        String extension =  currentFileName.substring(currentFileName.lastIndexOf("."),currentFileName.length());
                        Long nameRandom = Calendar.getInstance().getTimeInMillis();
                        
                        String newFileName = nameRandom+extension;
                        
                        fileSaveData = newFileName;
                        
                        String fileSavePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(this.path_to);
                        System.out.println("Save path "+fileSavePath);
                        
                        try{
                            byte[]  fileContent = new byte[(int) fileUpload.getSize()];
                            InputStream in = fileUpload.getInputStream();
                            in.read(fileContent);
                            File fileToCreate = new File(fileSavePath,newFileName);
                            File folder = new File (fileSavePath);
                            
                            if(!folder.exists()){
                                folder.mkdir();
                            }
                            
                            FileOutputStream fileOutStream = new FileOutputStream(fileToCreate); 
                            fileOutStream.write(fileContent);
                            fileOutStream.flush();
                            fileOutStream.close();
                            fileSaveData = newFileName; 
                        }catch(Exception e){
                            fileSaveData = null;
                            throw e;
                        }     
                    }
                }else{
                    fileSaveData = null;
                    System.out.println("No tiene formato valido");
                }    
            }else{
                System.out.println("No tiene nada");
            }
            
        }catch(Exception e){
            fileSaveData = null; 
            throw e;
        }
        
        return fileSaveData; 
    } 
    
    public boolean checkFileType(String fileName){
        if(fileName.length()>0){
            String[] parts=fileName.split("\\.");
            if(parts.length>0){
                String extension = parts[parts.length-1];
                return this.limit_type_file.contains(extension);
            }
        }
        return false;
    }
    
    public String getFileName(Part part){
        for(String cd : part.getHeader("content-disposition").split(";")){
            if(cd.trim().startsWith("filename")){
                String filename=cd.substring(cd.indexOf('=')+1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/')+1).substring(filename.lastIndexOf('\\')+1);
            }
        }
        return null;
    }
    
    public void deleteFile(String fileName){
        
        String fileSavePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(this.path_to);
        File fileToCreate = new File(fileSavePath+"/"+fileName);
        fileToCreate.delete();
        
    }
    
    
    
}
