package com.lvhao.schedulejob.domain;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.slf4j.Logger;
import org.springframework.util.ClassUtils;

/**
 * 作业DO
 *
 * @author: lvhao
 * @since: 2016-6-23 20:59
 */
public class JobDO {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JobDO.class);

    // job info
    private String name;
    private String group;
    private String targetClass;
    private String description;

    public JobDetail convert2QuartzJobDetail(){
        Class<? extends Job> clazz = null;
        try {
            clazz = (Class<Job>)ClassUtils.forName(this.targetClass,this.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            log.error("加载类错误",e);
        }
        return JobBuilder.newJob()
                .ofType(clazz)
                .withIdentity(this.name,this.getGroup())
                .withDescription(this.description)
                .build();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("JobDO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", group='").append(group).append('\'');
        sb.append(", targetClass='").append(targetClass).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}