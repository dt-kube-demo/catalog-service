package com.ewolff.microservice.catalog.cdc;

import com.ewolff.microservice.catalog.CatalogApp;
import com.ewolff.microservice.catalog.Item;
import com.ewolff.microservice.catalog.ItemRepository;
import com.ewolff.microservice.catalog.SpringRestDataConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = SpringRestDataConfig.class, loader = AnnotationConfigContextLoader.class)
public class CatalogConsumerDrivenContractTest {

	@InjectMocks
	private CatalogApp customerApp;

	@Mock
	private ItemRepository itemRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testConstructor() {
		CatalogApp catalogApp = new CatalogApp(itemRepository);

		assertNotNull(catalogApp);
	}

	@Test
	public void testGenerateTestData() {
		CatalogApp custApp = new CatalogApp(itemRepository);
		custApp.generateTestData();

    	verify(itemRepository, times(14)).save(any(Item.class));
	}



}
