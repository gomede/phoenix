//package br.com.phoenix.lib.core;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
//
//import br.com.phoenix.lib.core.impl.DeleteCommand;
//import br.com.phoenix.lib.core.impl.ListCommand;
//import br.com.phoenix.lib.core.impl.LoadByIDCommand;
//import br.com.phoenix.lib.core.impl.SaveCommand;
//
//@ContextConfiguration(locations = {
//		"classpath*:META-INF/dataSource-context-test.xml",
//		"classpath*:META-INF/phoenix-context-test.xml" })
//public class AppTest extends AbstractTransactionalJUnit4SpringContextTests {
//
//	protected final Logger logger = Logger.getLogger(getClass());
//
//	@Autowired
//	private CommandInvoker commandInvoker;
//
//	@Test
//	public void crud() {
//		logger.info("(C) Persistindo um novo objeto");
//
//		final EntityMock entityMock = new EntityMock();
//		entityMock.setNome("EntityMock");
//
//		Assert.assertNull(entityMock.getId());
//
//		final EntityMock mock = (EntityMock) commandInvoker.invokeInTransaction(new SaveCommand(entityMock));
//
//		logger.info(mock);
//
//		Assert.assertNotNull(mock.getId());
//
//
//
//		logger.info("(R) Carregando o objeto persistido");
//
//		final EntityMock entityMockLoaded = (EntityMock) commandInvoker.invokeInReadOnly(new LoadByIDCommand(EntityMock.class, mock.getId()));
//
//		logger.info(entityMockLoaded);
//
//		logger.info("Listando os objetos");
//
//		final List<EntityMock> list = (List<EntityMock>) commandInvoker.invokeInReadOnly(new ListCommand(EntityMock.class));
//
//		logger.info("Tamanho da lista : " + list.size());
//
//		for (EntityMock entity : list) {
//			logger.info(entity);
//		}
//
//
//		logger.info("(D) Removendo o objeto persistido");
//
//		final EntityMock entityMockDeleted = (EntityMock) commandInvoker.invokeInTransaction(new DeleteCommand(mock));
//
//		Assert.assertNull(entityMockDeleted);
//
//
//		final List<EntityMock> cleanList = (List<EntityMock>) commandInvoker.invokeInReadOnly(new ListCommand(EntityMock.class));
//
//		logger.info("Tamanho da lista : " + cleanList.size());
//
//		Assert.assertEquals(0, cleanList.size());
//
//	}
//}
