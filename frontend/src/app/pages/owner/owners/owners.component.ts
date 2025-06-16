import { Component, OnInit, inject } from '@angular/core';
import { LoadingComponent } from '../../../loading/loading.component';
import { IAllOwner, IOwner } from '../../../models/owner.model';
import { ApiService } from '../../../services/api.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgClass } from '@angular/common';
import { IRoleList } from '../../../models/enum.model';
declare var $: any;

@Component({
  selector: 'app-owners',
  standalone: true,
  imports: [LoadingComponent, ReactiveFormsModule, NgClass],
  templateUrl: './owners.component.html',
  styleUrl: './owners.component.css'
})
export class OwnersComponent implements OnInit {

  loading: boolean = true;
  listOwners: IAllOwner[] = [];
  enumRoles: IRoleList[] = [];


  private _apiService = inject(ApiService);
  private _router = inject(Router);

  ngOnInit(): void {
    this.obtenerOwners();
  }

  obtenerOwners() {
    this._apiService.getOwners().subscribe((data: IAllOwner[]) => {
      this.listOwners = data;
      this.loading = false;
    });
  }

  getRoles() {
    this._apiService.getRoles().subscribe((data: string[]) => {
      this.enumRoles = data.map((rol, index) => ({ 
        value: index + 1,
        roles: rol
      }));
    });
  }

  navegate(id: number): void {
    this._router.navigate(['owner', id]);
  }

  deleteOwner(id: number): void {
    if (confirm('¿Estás seguro del eliminar a este dueño?')) {
      this._apiService.deleteOwner(id);
    }
  }

  ownerCreateForm!: FormGroup
  ownerUpdateForm!: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.ownerCreateForm = this.createOwnerForm();
    this.ownerUpdateForm = this.createOwnerUpdateForm();
  }

  createOwnerForm(): FormGroup {
    return this.formBuilder.group({
      apellido: ['', [Validators.required, Validators.minLength(3)]],
      contacto: ['', [Validators.required, Validators.minLength(3)]],
      correo: ['', [Validators.required, Validators.minLength(3), Validators.email]],
      username: ['', [Validators.required, Validators.minLength(3)]],
      nombre: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      role: ['', [Validators.required]]
    });
  }


  enviar(event: Event) {
    event.preventDefault();

    const owner: IOwner = {
      apellido: this.ownerCreateForm.value.apellido,
      contacto: this.ownerCreateForm.value.contacto,
      correo: this.ownerCreateForm.value.correo,
      username: this.ownerCreateForm.value.username,
      nombre: this.ownerCreateForm.value.nombre,
      password: this.ownerCreateForm.value.password,
      roleId: this.ownerCreateForm.value.role
    };
    console.log(owner);
    this._apiService.addOwner(owner).subscribe({
      next: (response) => {
        console.log("Registro creado correctamente");
        this.obtenerOwners();
        $("#addCattleModal").modal('hide')
      },
      error: (error) => {
        console.log("Error al actualizar el registro");
      }
    });
  }

  /*--------------------------------------------------------------------------------------------------*/

  ownerUpdate: IOwner = {
    idOwner: undefined,
    apellido: '',
    contacto: '',
    correo: '',
    username: '',
    nombre: '',
    password: '',
    roleId: 0
  };

  loadOwner(id: number): void {
    this._apiService.getOwner(id).subscribe((data: any) => {
      this.ownerUpdate = data;
      console.log(data);
      this.ownerUpdate.password = '';
      this.ownerUpdateForm.patchValue(this.ownerUpdate);
    });
  }

  createOwnerUpdateForm(): FormGroup {
    return this.formBuilder.group({
      apellido: ['', [Validators.required, Validators.minLength(3)]],
      contacto: ['', [Validators.required, Validators.minLength(3)]],
      correo: ['', [Validators.required, Validators.minLength(3), Validators.email]],
      username: ['', [Validators.required, Validators.minLength(3)]],
      nombre: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      role: ['', [Validators.required]]
    });
  }

  enviarUpdate(event: Event) {
    event.preventDefault();

    const owner: IOwner = {
      idOwner: this.ownerUpdate.idOwner,
      apellido: this.ownerUpdateForm.value.apellido,
      contacto: this.ownerUpdateForm.value.contacto,
      correo: this.ownerUpdateForm.value.correo,
      username: this.ownerUpdateForm.value.username,
      nombre: this.ownerUpdateForm.value.nombre,
      password: this.ownerUpdateForm.value.password,
      roleId: this.ownerUpdateForm.value.role
    };
    console.log(owner);
    /*this._apiService.updateOwner(owner).subscribe({
      next: (response) => {
        console.log("Registro actualizado correctamente");
        this.obtenerOwners();
        $("#updateOwnerModal").modal('hide')
      },
      error: (error) => {
        console.log("Error al actualizar el registro");
      }
    });*/
  }

  hasErrors(field: string, typeError: string) {
    return this.ownerCreateForm.get(field)?.hasError(typeError) && this.ownerCreateForm.get(field)?.touched;
  }

  hasErrorsU(field: string, typeError: string) {
    return this.ownerUpdateForm.get(field)?.hasError(typeError) && this.ownerUpdateForm.get(field)?.touched;
  }
}
