/**
 * @author huDong
 */
package com.hu.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.hu.security.core.properties.SecurityProperties;

/**
 * @author Administrator
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
