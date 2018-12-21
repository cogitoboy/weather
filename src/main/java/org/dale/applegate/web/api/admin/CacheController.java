package org.dale.applegate.web.api.admin;

import org.stalesoft.data.CachableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class CacheController {

  @Autowired
  @Qualifier("cachedOpenWeatherDao")
  private CachableDao cachableDao;

  @RequestMapping(value = "/cache", method = RequestMethod.DELETE)
  public void clearCache() {
    synchronized (cachableDao.getClass()) {
      cachableDao.cacheEvict();
    }
  }
}
