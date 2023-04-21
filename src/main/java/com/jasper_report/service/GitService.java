package com.jasper_report.service;

import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;

public interface GitService {
    public Object getTablesList(String schemaName) throws GitAPIException, IOException;
}
