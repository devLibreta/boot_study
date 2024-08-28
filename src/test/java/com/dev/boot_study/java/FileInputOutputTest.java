package com.dev.boot_study.java;


import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class FileInputOutputTest {

//    private final Logger LOGGER = LoggerFactory.getLogger(FileInputOutputTest.class.getName());
    private final Logger LOGGER = LoggerFactory.getLogger(FileInputOutputTest.class.getSimpleName());

    /*

    ...inputStream, ...outputStream : 1byte 단위로 읽고 쓴다. ex) 파일일기
    ...reader, ...writer : 2byte 단위로 읽고 쓴다. (char) ex) 채팅

     */


    @Test
    @DisplayName("파일과 폴더 생성 테스트")
    // 테스트 내용
    // 1. 절대 경로 와 상대 경로로 File 클래스 생성, 파일 또는 폴더를 생성한다.
    // 2. File 내부의 메소드가 어떤 기능을 하는지 확인한다.
    public void getFileAttribute() throws IOException {
        File file = new File("devRun"); // 상대경로. root(C:\dev\project\file_board\)
        File absolutePathFile = new File("C:\\dev\\project\\file_board\\devRunAbsolute"); // 절대경로(역슬래시)
        File absolutePathFile2 = new File("C:/dev/project/file_board/devRunAbsolute2"); // 절대경로(슬래시)

        System.out.println("absolutePathFile.getAbsoluteFile() : "+absolutePathFile.getAbsoluteFile()); // C:\dev\project\file_board\devRunAbsolute
        System.out.println("absolutePathFile2.getAbsoluteFile() : "+absolutePathFile2.getAbsoluteFile()); // C:\dev\project\file_board\devRunAbsolute2

        System.out.println("file.createNewFile() :"+file.createNewFile()); // 파일 생성, 있으면 false. 경로상 중간폴더가 없으면 예외 발생
        System.out.println("file.length() :"+file.length()); // 3. 파일이 가지고 있는 byte 리턴한다.
        System.out.println("file.canExecute() :"+file.canExecute());
        System.out.println("file.canRead() :"+file.canRead());
        System.out.println("file.exists() :"+file.exists());
        System.out.println("file.canWrite() :"+file.canWrite());
        System.out.println("file.getAbsoluteFile() : "+file.getAbsoluteFile()); // C:\dev\project\file_board\devRun
        System.out.println("file.getAbsolutePath() : "+file.getAbsolutePath()); // C:\dev\project\file_board\devRun
        System.out.println("file.getName() : "+file.getName()); // devRun. 파일 이름
        System.out.println("file.getParent() : "+absolutePathFile.getParent()); // 부모 디렉토리 리턴(String). root 폴더는 null
        System.out.println("file.getParentFile() : "+absolutePathFile.getParentFile()); // 부모 디렉토리(File object)
        System.out.println("file.getPath() : "+file.getPath()); // devRun
        System.out.println("file.isAbsolute() :"+file.isAbsolute());
        System.out.println("file.isDirectory() :"+file.isDirectory()); // 디렉토리인가 아닌가?
        System.out.println("file.isFile() :"+file.isFile()); // 파일인가 아닌가?
        System.out.println("file.mkdir() :"+file.mkdir()); // 지정한 폴더를 생성
        System.out.println("file.mkdirs() :"+file.mkdirs()); // 지정한 폴더를 만드는데 필요한 중간 폴더도 다 생성.
        System.out.println("file.toPath() : "+file.toPath()); // devRun
        System.out.println("file.toString() : "+file.toString()); // devRun
        System.out.println("file.toURI() : "+file.toURI()); // file:/C:/dev/project/file_board/devRun
        System.out.println("file.getClass() : "+file.getClass()); // class java.io.File
        System.out.println("absolutePathFile2.delete() : "+file.delete()); // 삭제 성공 true, 실패 false
    }

    @Test
    @DisplayName("폴더 내 파일 또는 폴더 생성 테스트")
    // 테스트 내용
    // 1. 파일 생성과 폴더 생성 방법의 차이를 확인한다.
    // 2. 파일이 경로 상 존재하지 않는 폴더 안에 있어야 할 때 생성 순서를 확인한다.
    // 3. 파일이 폴더 안에 존재하는 경우 삭제 순서를 확인한다.
    public void createFileInDirectory() throws IOException {
        // File(parent,child) 생성자는 부모 디렉토리가 없다면 자식 파라미터 단독 생성자와 결과가 같다.
        // 생성 -> 경로 path 폴더를 만들고 그다음에 file 을 만들어야한다.
        // 삭제 -> 파일을 먼저 삭제하고 경로 path 폴더를 삭제해야한다.
        File fileInDir = new File("dir","file");
        File dirInDir = new File("dir","dir"); // 마지막에 슬래시 붙이는건 폴더,파일 생성과 관계없다.
        boolean result = fileInDir.renameTo(new File("dir","dir")); // 파일 이동

        // 생성 코드
        LOGGER.info("부모폴더 생성 : {}", fileInDir.getParentFile().mkdirs());
        LOGGER.info("부모폴더 안 파일 생성 : {}", fileInDir.createNewFile());
        LOGGER.info("부모폴더 안 디렉토리 생성 : {}", dirInDir.mkdir());

        // 파일과, 디렉토리가 생성되었는지 확인
        List<String> nameList =  Arrays.stream(Objects.requireNonNull(fileInDir.getParentFile().list())).toList(); // 디렉토리 File 일 경우 포함 파일 리스트를 String[]로 리턴.
        LOGGER.info("부모폴더 내 file/dir name 리스트 출력 : {}",nameList);
        LOGGER.info("부모폴더 안 파일이 만들어졌는가? : {}", fileInDir.isFile());
        LOGGER.info("부모폴더 안 폴더가 만들어졌는가? : {}", dirInDir.isDirectory());
        LOGGER.info("부모폴더는 폴더인가? : {}", dirInDir.getParentFile().isDirectory());

        // 삭제 코드
        LOGGER.info("부모폴더 안 파일 삭제 : {}", fileInDir.delete());
        LOGGER.info("부모폴더 안 폴더 삭제 : {}", dirInDir.delete());
        LOGGER.info("부모폴더 삭제 : {}", fileInDir.getParentFile().delete());
    }

    @Test
    @DisplayName("파일 또는 폴더 이름 변경 또는 이동 테스트")
    // 테스트 내용
    // 1. 파일과 디렉토리를 생성하는 방법을 확인한다.
    // 2. 파일과 디렉토리의 이름을 변경하는 방법을 확인한다.
    // 3. 파일과 디렉토리를 이동하는 방법을 확인한다.
    // 4. 파일과 디렉토리를 삭제하는 방법을 확인한다.
    // File 은  안 잡는 Exception이 많기 때문에 로그 값이 전부 true 이어야 정상이다.
    public void renameOrMoveFile() throws IOException {
        // File
        File file = new File("renameOrMoveFile");
        File directory = new File("renameOrMoveDir");
        // 파일 및 디렉토리 생성
        System.out.println("file.createNewFile() : "+file.createNewFile());
        System.out.println("directory.mkdir() : "+directory.mkdir());
//        System.out.println("directory.mkdirs() : "+directory.mkdirs()); // 존재하지 않는 서브 디렉토리 생성
        // 파일 및 디렉토리 이름 변경
        File file2 = new File("renameOrMoveFile2");
        File dir2 = new File("renameOrMoveDir2");
        System.out.println("file.renameTo(file2)) : "+file.renameTo(file2));
        System.out.println("directory.renameTo(dir2)) : "+directory.renameTo(dir2));

        // 이동용 디렉토리 생성
        File moveToThisDir = new File("moveToThisDir");
        System.out.println("moveToThisDir.mkdirs() : "+moveToThisDir.mkdirs());

        // 파일 및 디렉토리를 이동용 디렉토리로 이동
        File file3 = new File("moveToThisDir/renameOrMoveFile3");
        File dir3 = new File("moveToThisDir/renameOrMoveDir3");
        System.out.println("file2.renameTo(moveToThisDir) : "+file2.renameTo(file3));
        System.out.println("dir2.renameTo(moveToThisDir) : "+dir2.renameTo(dir3));

        // 메소드로 생성된 파일 디렉토리 삭제
        System.out.println("deleteFile.delete() : "+file3.delete());
        System.out.println("deleteDir.delete() : "+dir3.delete());
        System.out.println("moveToThisDir.delete() : "+moveToThisDir.delete());
    }
    @Test
    @DisplayName("파일 동등성 테스트")
    // 같은 path 에서 생성한 File 클래스가 서로 동등한지 확인한다.
    public void isSameFile(){
        File file = new File("devRun");
        File file2 = new File("devRun");
        boolean isSameFile = file.equals(file2);
        System.out.println("isSameFile : "+isSameFile); // true
    }

    @Test
    @DisplayName("파일 데이터 input 테스트")
    // 테스트 내용
    // 1. File 에 데이터를 입력하는 방법과 옵션(덮어쓰기, 이어쓰기)을 확인한다.
    // 2. File 에 데이터를 입력할 시 발생할 수 있는 예외를 확인한다.
    public void writeFile(){
        File file = new File("devRun");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file,true); //true 이어쓰기, false(*) 덮어쓰기
            fos.write(65); // 65는 'A'
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(fos != null){
                try{
                    fos.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    @DisplayName("파일 데이터 read 테스트")
    // 테스트 내용
    // 1. File 에 데이터를 읽는 방법을 확인한다.
    // 2. FIle 에 데이터를 읽을 시 발생할 수 있는 예외를 확인한다.
    public void readFile() {
        File file = new File("devRun"); // 상대 경로. 기본 프로젝트 폴더가 root 로 인식함.
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            int data = 0;
            while((data=fis.read())!=-1){ // EOF(End of file) 일 경우 -1을 리턴한다.
                System.out.println("(char)data : "+(char)data); // A
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try{
                    fis.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
