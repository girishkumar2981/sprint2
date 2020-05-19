package com.cg.anurag.b2.imsdrs;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import com.cg.anurag.b2.imsdrs.dto.RawMaterialSpecs;
import com.cg.anurag.b2.imsdrs.service.RawMaterialSpecsService;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RawMaterialSpecsApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;
	public void setTestRestTemplate(TestRestTemplate testRestTemplate)
	{
		this.testRestTemplate = testRestTemplate;
	}
	@LocalServerPort
	int serverPort;
	@Autowired
	RawMaterialSpecsService rawMaterialSpecsService;
	@Test
public void getRawMaterialSpecs_Positive() throws Exception
{
	List<RawMaterialSpecs> rawMaterialSpecs = rawMaterialSpecsService.getAllRawMaterialSpecs();
	Assertions.assertEquals(2, rawMaterialSpecs.size());
}
	@Test
	public void getRawMaterialSpecs_Negative() throws Exception
	{
		List<RawMaterialSpecs> rawMaterialSpecs = rawMaterialSpecsService.getAllRawMaterialSpecs();
		Assertions.assertNotSame(3, rawMaterialSpecs.size());
	}
}
