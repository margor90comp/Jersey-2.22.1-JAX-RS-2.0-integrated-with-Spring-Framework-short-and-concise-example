package pl.margor.rest.app;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class RestApp extends Application {

    @Context
    protected ServletContext servletContext;

    @Override
    public Set<Object> getSingletons() {
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        AutowireCapableBeanFactory bf = ctx.getAutowireCapableBeanFactory();
        Set set = new HashSet();
        set.add(bf.getBean("personRest"));
        return set;
    }

}