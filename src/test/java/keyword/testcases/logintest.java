package keyword.testcases;

import org.testng.annotations.Test;

import keyword_util.ExecutionEngine;

public class logintest {
	public class LoginTest {
		public ExecutionEngine KeywordEngine;
		public  String sheetname = "fb_keyword";


		@Test
		public void loginTest() throws InterruptedException {
			KeywordEngine = new ExecutionEngine();
			KeywordEngine.startExecution(sheetname);
		}

	}

}
