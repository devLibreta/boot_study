package com.dev.boot_study.java;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class PathInputOutputTest {
    /*
    java.io
        - I/O 방식: File Stream
        - Buffer 방식: Non-Buffer
        - Asynchronous 방식: 지원 안함
        - Blocking: Only- Blocking 지원
    java.nio
        - I/O 방식: File Channel
        - Buffer 방식: Buffer
        - Asynchronous 방식: 지원함
        - Blocking: Blocking/Non-blocking 지원

    File(old) -> Path(new)+Files(new)
     */

    @Test
    @DisplayName("Path 클래스 속성 테스트")
    // 테스트 내용
    // 1. 절대 경로 와 상대 경로로 File 클래스 생성, 파일 또는 폴더를 생성방법을 확인한다..
    public void pathAttribute() throws IOException {
        // Path 생성자의 경우 root 를 뜻하는 /를 넣지 않고 디렉토리명이나 파일명만 입력한다.
        Path path = Paths.get("devRunPath");
        Path absolutePath = Paths.get("C:\\dev\\project\\file_board\\devRunPathAbsolute");

        // File 과 Path 상호 변환.
        File file = path.toFile();
        Path path2 = file.toPath();

        System.out.println("path : "+path); // \devRunPath
        System.out.println("absolutePath : "+absolutePath); // C:\dev\project\file_board\devRunPathAbsolute

        System.out.println("Files.createFile(path) : "+Files.createFile(path));
        System.out.println("Files.createFile(absolutePath) : "+Files.createFile(absolutePath));

        System.out.println("path.getFileName() : "+path.getFileName()); // devRun
        System.out.println("path.getParent().getFileName() : "+path.getParent().getFileName()); // null
        System.out.println("path.getRoot() : "+path.getRoot()); // \
        System.out.println("path.getFileSystem() : "+path.getFileSystem()); // sun.nio.fs.WindowsFileSystem@68c4a860
        System.out.println("path.getNameCount() : "+path.getNameCount()); // 1

        for(int i=0;i<path.getNameCount(); i++){
            System.out.println((i+1)+"단계 : "+path.getName(i)); // 1단계 : devRun
        }
    }
    @Test
    // 테스트 내용
    // 1. 중첩된 디렉토리 생성방법을 확인한다.
    // 2. 중첩된 디렉토리 내부에 파일 생성방법을 확인한다.
    public void pathInNestedDir() throws IOException {
        Path pathInNestedDir = Paths.get("dirPath","dir1","dir2","filePath");
        Path pathInNestedDirAnother = Paths.get("dirPath").resolve("dir").resolve("dir2").resolve("filePath");

        System.out.println("Files.createDirectories(pathInNestedDir) : "+Files.createDirectories(pathInNestedDir.getParent())); // Path의 마지막 요소까지 디렉토레 생성.
        System.out.println("pathInNestedDir : "+pathInNestedDir); // dirPath\dir1\dir2\filePath
        System.out.println("Files.createFile(pathInNestedDir) : "+Files.createFile(pathInNestedDir)); // path에 지정한 곳에 파일 생성
    }

    @Test
    // 테스트 내용
    // 1. 파일과 디렉토리를 생성하는 방법을 확인한다.
    // 2. 파일과 디렉토리의 이름을 변경하는 방법을 확인한다.
    // 3. 파일과 디렉토리를 이동하는 방법을 확인한다.
    // 4. 파일과 디렉토리를 삭제하는 방법을 확인한다.
    public void renameOrMovePath() throws IOException {
        // 파일과 디렉토리 생성
        Path pathFile = Paths.get("renameOrMoveFile");
        Path pathDir = Paths.get("renameOrMoveDir");
        System.out.println("Files.createFile(pathFile) : "+Files.createFile(pathFile));
        System.out.println("Files.createDirectory(pathDir) : "+Files.createDirectory(pathDir));
//        System.out.println("Files.createDirectories(pathDir) : "+Files.createDirectories(pathDir)); // 존재하지 않는 서브 디렉토리 생성
        // 파일과 디렉토리 이름 변경
        Path pathFile2 = Paths.get("renameOrMoveFile2");
        Path pathDir2 = Paths.get("renameOrMoveDir2");
        System.out.println(Files.move(pathFile,pathFile2));
        System.out.println(Files.move(pathDir,pathDir2));

        // 파일과 디렉토리 이동용 폴더 생성.
        Path moveToThisDir = Paths.get("moveToThisDir");
        System.out.println("Files.createDirectory(moveToThisDir) : "+Files.createDirectory(moveToThisDir));

        // 파일과 디렉토리 이동
        Path pathFile3 = Paths.get("moveToThisDir","renameOrMoveFile2");
        Path pathDir3 = Paths.get("moveToThisDir","renameOrMoveDir2");
        System.out.println("Files.move(pathFile2,pathFile3) : "+Files.move(pathFile2,pathFile3));
        System.out.println("Files.move(pathDir2,pathDir3) : "+Files.move(pathDir2,pathDir3));

        // 메소드로 생성된 파일과 디렉토리 삭제
        System.out.println("Files.deleteIfExists(pathFile3) : "+Files.deleteIfExists(pathFile3));
        System.out.println("Files.deleteIfExists(pathDir3) : "+Files.deleteIfExists(pathDir3));
        System.out.println("Files.deleteIfExists(moveToThisDir) : "+Files.deleteIfExists(moveToThisDir));
    }
}
