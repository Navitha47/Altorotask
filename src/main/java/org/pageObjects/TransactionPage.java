package org.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utility.CommonUtility;

public class TransactionPage extends CommonUtility {
	public TransactionPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "MenuHyperLink2")
	private WebElement viewTransaction;

	@FindBy(xpath = "//table[contains(@id,'MyTransactions')]")
	private WebElement transactionTable;

	@FindBy(tagName = "tr")
	private List<WebElement> tableRows;

	@FindBy(tagName = "td")
	private List<WebElement> tableDatas;

	public WebElement getViewTransaction() {
		return viewTransaction;
	}

	public WebElement getTransactionTable() {
		return transactionTable;
	}

	

}
