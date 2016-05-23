package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;
import rocketData.LoanRequest;

public class rate_test {
	@Test
	public void TestCreditScore() throws RateException {
		int iActualCreditScore = 720;
		double iExpectedInterestRate = 4.0;
		double iActualInterestRate;
		iActualInterestRate = RateBLL.getRate(iActualCreditScore);
		assert(iActualInterestRate == iExpectedInterestRate);
	}
	
	@Test(expected = RateException.class)
	public void TestBadCreditScore() throws RateException {
		int iActualCreditScore = 550;
		double iActualInterestRate;
		iActualInterestRate = RateBLL.getRate(iActualCreditScore);
	}
	private static final double DELTA = 1e-2;
	
	@Test
	public void getPaymentTest(){
		double pmt = Math.abs(RateBLL.getPayment((0.04)/12, 360, 300000, 0, false));
		double iExpectedPayment = 1432.25;
		double iActualPayment = pmt;
		assertEquals(iExpectedPayment, iActualPayment, DELTA);
		System.out.printf("Payment is: %f" ,pmt);
	}
	
	@Test
	public void checkIncomeExpensesTrueTest(){
		LoanRequest lq = new LoanRequest();
		lq.setdAmount(1432);
		lq.setIncome(100000);
		lq.setExpenses(4000);
		
		assertTrue(RateBLL.checkIncomeExpenses(lq) == true);
	}
	
	@Test
	public void checkIncomeExpenseFalseTest(){
		LoanRequest lq = new LoanRequest();
		lq.setdAmount(1432);
		lq.setIncome(5000);
		lq.setExpenses(1500);
		assertTrue(RateBLL.checkIncomeExpenses(lq) == false);
	}
}
