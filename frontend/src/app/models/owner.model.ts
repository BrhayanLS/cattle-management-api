export interface IAllOwner {
    idOwner:  number;
    estado:   boolean;
    nombre:   string;
    apellido: string;
    username: string;
    contacto: string;
    correo:   string;
    password: string;
    role:     Role;
}

export interface Role {
    id:   number;
    name: null | string;
}

export interface IOwner {
    idOwner?:  number;
    nombre:   string;
    apellido: string;
    contacto: string;
    correo:   string;
    username: string;
    password: string;
    roleId:   number;
}

