package com.jasper_report.serviceImpl;

import com.jasper_report.service.GitService;
import lombok.extern.log4j.Log4j2;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
@Log4j2
public class GitServiceImpl implements GitService {

//    @Autowired
//    private Git git;

    @Override
    public Object getTablesList(String schemaName) throws GitAPIException, IOException {

        File localPath2 = File.createTempFile("TestGitRepository", "");
        localPath2.delete();

          Git git = Git.cloneRepository()
                .setURI("https://github.com/madhumangali/Json.git")
                  .setCredentialsProvider(new UsernamePasswordCredentialsProvider("madhumangali", "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"))
                   .setBranch("master")
                   .setDirectory(localPath2)
                .call();
          git.pull().call();
          File file=new File( localPath2+"/madhu.txt");
          file.createNewFile();
        FileWriter myWriter = new FileWriter(localPath2+"/madhu.txt");
        myWriter.write("Files in Java might be tricky, but it is fun enough!");
        myWriter.close();
        git.add().addFilepattern(".").call();
        git.commit().setMessage("Message").call();
        PushCommand pushCommand= git.push();
        pushCommand
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider("madhumangali", "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"))
                .call();

        return null;
    }
}
