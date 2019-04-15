package com.fd.mybatis.factory;

import java.lang.reflect.ReflectPermission;

/**
 * @Description 写点注释吧
 * @Author fengdi
 * @Version V1.0.0
 * @ClassName Reflector
 * @Date 2019-04-15 09:29
 */
public class Reflector {

    /**
     * Checks whether can control member accessible.
     *
     * @return If can control member accessible, it return {@literal true}
     * @since 3.5.0
     */
    public static boolean canControlMemberAccessible() {
        try {
            SecurityManager securityManager = System.getSecurityManager();
            if (null != securityManager) {
                securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
            }
        } catch (SecurityException e) {
            return false;
        }
        return true;
    }

}
