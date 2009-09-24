/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.fs;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.apache.hadoop.security.UnixUserGroupInformation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestHDFSFileContextMainOperations extends
                                  FileContextMainOperationsBaseTest {
  
  private static MiniDFSCluster cluster;
  private static Path defaultWorkingDirectory;
  
  @BeforeClass
  public static void clusterSetupAtBegining()
                                    throws IOException, LoginException  {
    cluster = new MiniDFSCluster(new Configuration(), 2, true, null);
    fc = FileContext.getFileContext(cluster.getFileSystem());
    defaultWorkingDirectory = fc.makeQualified( new Path("/user/" + 
        UnixUserGroupInformation.login().getUserName()));
    fc.mkdir(defaultWorkingDirectory, FileContext.DEFAULT_PERM, true);
  }

      
  @AfterClass
  public static void ClusterShutdownAtEnd() throws Exception {
    cluster.shutdown();   
  }
  
  @Before
  public void setUp() throws Exception {
  }
  
  @Override
  @After
  public void tearDown() throws Exception {
    super.tearDown();
  }

  @Override
  protected Path getDefaultWorkingDirectory() {
    return defaultWorkingDirectory;
  } 
  
  @Override
  @Test
  public void testRenameFileAsExistingFile() throws Exception {
    // ignore base class test till hadoop-6240 is fixed
  }
}
