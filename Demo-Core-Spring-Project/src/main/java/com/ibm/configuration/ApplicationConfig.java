/**
 * 
 */
package com.ibm.configuration;

import org.springframework.context.annotation.Import;

/**
 * @author 003OQT744
 *
 */
@Import({HelloWorldConfig.class,CustomerConfig.class})
public class ApplicationConfig {

}
