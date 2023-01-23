package com.speakingclock;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speakingclock.services.SpeakingClockService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpeakingClockTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Mock
	private SpeakingClockService speakingClockService;

	@Mock
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test 
	public void test1() throws Exception {
		

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.get("/speakingCLock/convertTime/5:05")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status =mvcResult.getResponse().getStatus();
		assertEquals(200, status); 	
		String responseString =mvcResult.getResponse().getContentAsString();
	
        assertEquals("It's five oh five AM", responseString);
	}

	@Test 
	public void test2() throws Exception {
		

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.get("/speakingCLock/convertTime/12,13")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status =mvcResult.getResponse().getStatus();
		assertEquals(200, status); 	
		String responseString =mvcResult.getResponse().getContentAsString();
	
        assertEquals("You need to pass a valid 24 hour format hour", responseString);
	}
	
	@Test 
	public void test3() throws Exception {
		

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.get("/speakingCLock/convertTime/12:00")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status =mvcResult.getResponse().getStatus();
		assertEquals(200, status); 	
		String responseString =mvcResult.getResponse().getContentAsString();
	
        assertEquals("It's Midday", responseString);
	}
	
	@Test 
	public void test4() throws Exception {
		

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.get("/speakingCLock/convertTime/00:00")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status =mvcResult.getResponse().getStatus();
		assertEquals(200, status); 	
		String responseString =mvcResult.getResponse().getContentAsString();
	
        assertEquals("It's Midnight", responseString);
	}
	
	@Test 
	public void test5() throws Exception {
		

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.get("/speakingCLock/convertTime/14:75")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status =mvcResult.getResponse().getStatus();
		assertEquals(200, status); 	
		String responseString =mvcResult.getResponse().getContentAsString();
        assertEquals("You need to pass a valid 24 hour format hour", responseString);

     
	}
	@Test 
	public void test6() throws Exception {
		

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
				.get("/speakingCLock/convertTime/24:15")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		int status =mvcResult.getResponse().getStatus();
		assertEquals(200, status); 	
		String responseString =mvcResult.getResponse().getContentAsString();
        assertEquals("You need to pass a valid 24 hour format hour", responseString);

     
	}
	

	/*
	 * @Rule public ExpectedException exceptionRule = ExpectedException.none();
	 * 
	 * @Test public void
	 * whenEnteredLettersInsteadOfIntegers_thenShouldReturnException() {
	 * exceptionRule.expect(NumberFormatException.class);
	 * exceptionRule.expectMessage("You need to pass a valid 24 hour format hour");
	 * SpeakingClockService speakingClock = new
	 * SpeakingClockService().convertTime("12:aa"); speakingClock.solve(); }
	 * 
	 * @Test public void
	 * whenEnteredSemicolonInsteadOfColon_thenShouldReturnException() {
	 * exceptionRule.expect(NumberFormatException.class);
	 * exceptionRule.expectMessage("You need to pass a valid 24 hour format hour");
	 * SpeakingClock speakingClock = new SpeakingClock("12;13");
	 * speakingClock.solve(); }
	 * 
	 * @Test public void whenEnteredMidday_thenShouldReturnItsMidday() {
	 * SpeakingClock speakingClock = new SpeakingClock("12:00");
	 * speakingClock.solve(); assertEquals("It's Midday",
	 * speakingClock.getResult()); }
	 * 
	 * @Test public void whenEnteredMidnight_thenShouldReturnItsMidnight() {
	 * SpeakingClock speakingClock = new SpeakingClock("24:00");
	 * speakingClock.solve(); assertEquals("It's Midnight",
	 * speakingClock.getResult()); }
	 * 
	 * @Test public void whenEnteredTwelveFifty_thenShouldReturnItsTwelveFifty() {
	 * SpeakingClock speakingClock = new SpeakingClock("12:50");
	 * speakingClock.solve(); assertEquals("It's twelve fifty ",
	 * speakingClock.getResult()); }
	 * 
	 * @Test public void whenEnteredOne_thenShouldReturnItsOne() { SpeakingClock
	 * speakingClock = new SpeakingClock("13:00"); speakingClock.solve();
	 * assertEquals("It's one ", speakingClock.getResult()); }
	 * 
	 * @Test public void whenEnteredTwoOhSix_thenShouldReturnItsTwoOhSix() {
	 * SpeakingClock speakingClock = new SpeakingClock("14:06");
	 * speakingClock.solve(); assertEquals("It's two oh six ",
	 * speakingClock.getResult()); }
	 * 
	 * @Test public void whenEnteredFiveFifteen_thenShouldReturnItsFiveFifteen() {
	 * SpeakingClock speakingClock = new SpeakingClock("17:15");
	 * speakingClock.solve(); assertEquals("It's five fifteen ",
	 * speakingClock.getResult()); }
	 */
}
