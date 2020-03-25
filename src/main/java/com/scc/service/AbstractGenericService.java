package com.scc.service;

public abstract class AbstractGenericService<T> {

    protected Class<T> type;

    protected Class<T> getType() {
        return type;
    }

    protected void setType(Class<T> type) {
        this.type = type;
    }

    
    /**
     * Le rôle détermine l'affichage de l'objet lors de la serialization
     * Le rôle est déclaré par le biais d'une annotation sur l'objet
     * @return
     
    public boolean hasRole() {

        //System.out.println("Start hasRole");
        boolean result = false;

        try {

            String _role = null;
            if (getType().isAnnotationPresent(Role.class)) {
                _role = getType().getAnnotation(Role.class).name();
            }
            if ("".equals(_role) || _role == null) {
                return false;
            }
            if (identity.hasRole(_role))
                result = true;
            else
                result = false;

        } catch (Exception e) {
            //System.out.println("Error hasRole" + e.getMessage());
        } finally {
            //System.out.println("End hasRole");
        }
        return result;
    }
    */
    
}
