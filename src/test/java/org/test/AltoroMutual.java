package org.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.pageObjects.AccountsPage;
import org.pageObjects.ContactPage;
import org.pageObjects.LoginPage;
import org.pageObjects.SignOffPage;
import org.pageObjects.TransactionPage;
import org.pageObjects.TransferPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.utility.CommonUtility;

public class AltoroMutual extends CommonUtility{
	
	@BeforeClass
	private void setup() {
//		Browser and Url SetUp
		launchBrowser("Chrome");
		launchUrl("http://testfire.net/index.jsp");
		implicitWait(20);
	}
	
	@AfterMethod
	private void afterTestScreenShots(ITestResult r) throws IOException {
//		Screenshots of Test Results
		takeScreenShot(r.getName());
	}
	
	@AfterClass
	private void tearDown() {
//		Final TearDown of Browser
		quit();
	}
	
	@Test(priority=1)
	private void loginValidation() {
		LoginPage login = new LoginPage();
		btnClick(login.getSignIn());
// 		invalid login
		sendkeys(login.getUsername(), "demo_user");
		sendkeys(login.getPassword(), "demo_password");
		btnClick(login.getLoginButton());
// 		valid login
		sendkeys(login.getUsername(), "admin");
		sendkeys(login.getPassword(), "admin");
		btnClick(login.getLoginButton());
//		Valid Login Assertion
		boolean hello = login.getProfileName().getText().contains("Hello Admin User");
		Assert.assertTrue(hello,"Verify the Login Valid Credentials");
		System.out.println(login.getProfileName().getText());
	}
	
	@Test(priority=2)
	private void verifyTheBalance() {
		AccountsPage ac = new AccountsPage();
		btnClick(ac.getAccountSummary());
//		Account Selection
		selectByValue(ac.getAccountList(), "800001");
		btnClick(ac.getGoButton());
//		Getting The Balance Amount
		String avail = ac.getAvailableBalance().getText();
		String end = ac.getEndingBalance().getText();
//		Balance Assertion
		Assert.assertEquals(avail, end,"Verify The Available balance Status");
		System.out.println(ac.getAvailableBalance().getText());
	}
	
	@Test(priority=3)
	private void transeferFund() {
		TransferPage tf = new TransferPage();
		btnClick(tf.getTransferFunds());
//		Selection of From and To Account
		selectByValue(tf.getFromAccount(), "800000");
		selectByValue(tf.getToAccount(), "800001");
//		Amount Transfer
		sendkeys(tf.getTxtAmount(), "9876");
		btnClick(tf.getBtnTransfer());
//		Amount Verification - Assert
		boolean trxMsg = tf.getTransferMessage().getText().contains("9876");
		Assert.assertTrue(trxMsg, "Transfer Amount verication");
		System.out.println(getText(tf.getTransferMessage()));
	}
	
	@Test(priority=4)
	private void transcationCheck() {
		TransactionPage tc = new TransactionPage();
		btnClick(tc.getViewTransaction());
//		Taking All Rows From The Table
		List<WebElement> tbrows = tc.getTransactionTable().findElements(By.tagName("tr"));
//		Verifying First and Second Row Datas With The Transfered Amount
		boolean fstData = tbrows.get(1).getText().contains("9876");
		boolean sndData = tbrows.get(2).getText().contains("9876");
//		Assertion
		Assert.assertEquals(fstData, sndData,"Verify The First Two transactions ");
		System.out.println("The transaction history was tested");
	}
	
	@Test(priority=5)
	private void ContactMsgValidation() {
		ContactPage ct = new ContactPage();
//		Contact Page Navigation
		btnClick(ct.getContactUslink());
		btnClick(ct.getOnlineForm());
//		Contact Page Filling of Details
		sendkeys(ct.getTxtemail(), "navitha@gmail.com");
		sendkeys(ct.getTxtSubject(), "Interview");
		sendkeys(ct.getTxtComments(), "Testing completed");
		btnClick(ct.getBtnSubmit());
//		Asserting The Confirmation Message
		String msg = ct.getMsgThanku().getText();
		Assert.assertEquals(msg, "Thank You","Verify The Contact Page Message");
		System.out.println("User Successfully submit the feedback");
	}
	
	@Test(priority=6)
	private void signOff() {
		SignOffPage s = new SignOffPage();
//		Signing Off
		btnClick(s.getSignOffButton());
//		Asserting whether Signed Off or Not
		boolean signOff = s.getSignOffButton().getText().contains("Sign In");
		Assert.assertTrue(signOff,"Verify the sign off");
		System.out.println("User Successfully sign off the application");
	}
	
}
