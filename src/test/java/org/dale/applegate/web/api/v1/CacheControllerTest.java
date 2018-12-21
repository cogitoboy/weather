package org.dale.applegate.web.api.v1;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.stalesoft.data.CachableDao;
import org.dale.applegate.web.api.admin.CacheController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CacheControllerTest {


  @Mock
  CachableDao cachableDaoMock;

  @InjectMocks
  CacheController cacheController;

  @Test
  public void testClearCache() {
    cacheController.clearCache();
    verify(cachableDaoMock, times(1)).cacheEvict();
  }

}
