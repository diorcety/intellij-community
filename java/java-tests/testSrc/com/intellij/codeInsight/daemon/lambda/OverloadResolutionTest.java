/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.codeInsight.daemon.lambda;

import com.intellij.codeInsight.daemon.LightDaemonAnalyzerTestCase;
import com.intellij.codeInspection.deadCode.UnusedDeclarationInspection;
import com.intellij.openapi.projectRoots.JavaSdkVersion;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.testFramework.IdeaTestUtil;
import org.jetbrains.annotations.NonNls;

public class OverloadResolutionTest extends LightDaemonAnalyzerTestCase {
  @NonNls static final String BASE_PATH = "/codeInsight/daemonCodeAnalyzer/lambda/overloadResolution";

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    enableInspectionTool(new UnusedDeclarationInspection());
  }

  public void testPertinentToApplicabilityOfExplicitlyTypedLambda() throws Exception {
    doTest();
  }

  public void testVoidValueCompatibilityOfImplicitlyTypedLambda() throws Exception {
    doTest();
  }
  
  public void testVoidValueCompatibilityCachedControlFlow() throws Exception {
    doTest();
  }

  public void testVoidValueCompatibilityCanCompleteNormallyWithCallWithExceptionAsLastStatement() throws Exception {
    doTest();
  }

  public void testVoidValueCompatibilityCantCompleteNormallyWithCallWithExceptionAsLastReturnStatement() throws Exception {
    doTest();
  }

  public void testValueCompatibleWithThrowsStatement() throws Exception {
    doTest(false);
  }

  public void testIDEA102800() throws Exception {
    doTest();
  }

  public void testReturnStatementsInsideNestedLambdasDuringVoidValueCompatibilityChecks() throws Exception {
    doTest();
  }

  public void testIgnoreNonFunctionalArgumentsWhenCheckIfFunctionalMoreSpecific() throws Exception {
    doTest();
  }

  public void testLambdaIsNotCongruentWithFunctionalTypeWithTypeParams() throws Exception {
    doTest();
  }

  public void testDetectPolyExpressionInReturnsOfExplicitlyTypedLambdaWhenPrimitiveCouldWin() throws Exception {
    doTest();
  }

  private void doTest() {
    doTest(true);
  }

  private void doTest(boolean warnings) {
    IdeaTestUtil.setTestVersion(JavaSdkVersion.JDK_1_8, getModule(), getTestRootDisposable());
    doTest(BASE_PATH + "/" + getTestName(false) + ".java", warnings, false);
  }

  @Override
  protected Sdk getProjectJDK() {
    return IdeaTestUtil.getMockJdk18();
  }
}
