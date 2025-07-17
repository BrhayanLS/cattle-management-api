package com.adgan.persitence.entity;

/**
 * Enumeración que define los tipos de roles disponibles en el sistema.
 * Cada rol tiene diferentes niveles de acceso y permisos.
 *
 * @author BrhayanLS
 * @version 1.0
 * @since 2024
 */
public enum ERole {
    /**
     * Rol de administrador con acceso total al sistema.
     */
    ADMIN,
    
    /**
     * Rol de usuario normal con acceso limitado.
     */
    USER,
    
    /**
     * Rol de invitado con acceso mínimo al sistema.
     */
    INVITED
}