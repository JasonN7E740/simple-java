/*
 *   Copyright 2019 Jasha Joachimsthal
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package eu.jasha.demo.sbtfragments;

import eu.jasha.demo.sbtfragments.config.MysqlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Connection;
import java.util.Objects;

@Controller
@Slf4j
public class HomeController {

  static final String VIEW_INDEX = "pages/index";

  @GetMapping(value = "")
  public String getHome() {
    if (Objects.isNull(mysql)) {
      mysql = new MysqlProperties();
    }
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setUsername("root");
    driverManagerDataSource.setPassword(mysql.getPassword());
    driverManagerDataSource.setUrl(String.format("jdbc:mysql://%s:%s?useUnicode=true&useSSL=false",mysql.getHost(),mysql.getPort()));
    driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

    try {
      DataSourceUtils.getConnection(driverManagerDataSource);
    } catch (CannotGetJdbcConnectionException e) {
      log.error("检测mysql环境变量注入：没有任何业务上的意义，请忽略掉这个信息 \n mysql.host='{}', mysql.port= '{}', mysql.password='{}'", mysql.getHost(), mysql.getPort(), mysql.getPassword());
    }
    return VIEW_INDEX;
  }

  @Resource
  private MysqlProperties mysql;

}