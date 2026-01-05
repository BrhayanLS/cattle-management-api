import { Component, EventEmitter, Input, OnInit, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { IRoleList } from '../../../models/enum.model';
import { ApiService } from '../../../services/api.service';
import { IOwner } from '../../../models/owner.model';
import { NgClass } from '@angular/common';

@Component({
    selector: 'app-owner-form',
    standalone: true,
    imports: [ReactiveFormsModule, NgClass],
    templateUrl: './owner-form.component.html',
    styleUrl: './owner-form.component.css'
})
export class OwnerFormComponent implements OnInit {

    @Input() ownerData?: IOwner;
    @Output() onSave = new EventEmitter<void>();
    @Output() onCancel = new EventEmitter<void>();

    ownerForm!: FormGroup;
    enumRoles: IRoleList[] = [];
    private _apiService = inject(ApiService);
    private _fb = inject(FormBuilder);

    ngOnInit(): void {
        this.getRoles();
        this.initForm();

        if (this.ownerData) {
            this.populateForm(this.ownerData);
        }
    }

    getRoles() {
        this._apiService.getRoles().subscribe((data: string[]) => {
            this.enumRoles = data.map((rol, index) => ({
                value: index + 1,
                roles: rol
            }));
        });
    }

    initForm() {
        this.ownerForm = this._fb.group({
            nombre: ['', [Validators.required, Validators.minLength(3)]],
            apellido: ['', [Validators.required, Validators.minLength(3)]],
            username: ['', [Validators.required, Validators.minLength(3)]],
            correo: ['', [Validators.required, Validators.minLength(3), Validators.email]],
            contacto: ['', [Validators.required, Validators.minLength(10), Validators.pattern('^[0-9]*$')]], // Added pattern for numbers only
            password: ['', [Validators.minLength(8)]], // Optional in edit, required logic handled in submit if new
            role: ['', [Validators.required]]
        });

        if (!this.ownerData) {
            this.ownerForm.get('password')?.addValidators(Validators.required);
            this.ownerForm.get('password')?.updateValueAndValidity();
        }
    }

    populateForm(data: IOwner) {
        this.ownerForm.patchValue({
            nombre: data.nombre,
            apellido: data.apellido,
            username: data.username,
            correo: data.correo,
            contacto: data.contacto,
            role: data.roleId
            // Password is left empty for security, only set if changing
        });
        // Remove password requirement in edit mode if it's optional to change
        this.ownerForm.get('password')?.clearValidators();
        if (this.ownerData?.password) {
            // If we strictly want to require password on update, keep it, but usually it's optional
            // logic is complex without backend support for "optional password update".
            // The original code reset password to string '' on load.
            this.ownerForm.get('password')?.setValidators([Validators.minLength(8)]);
        }
        this.ownerForm.get('password')?.updateValueAndValidity();
    }

    onSubmit() {
        if (this.ownerForm.invalid) {
            this.ownerForm.markAllAsTouched();
            return;
        }

        const formValue = this.ownerForm.value;

        // Construct payload
        const ownerPayload: IOwner = {
            ...this.ownerData, // Preserve ID if exists
            nombre: formValue.nombre,
            apellido: formValue.apellido,
            username: formValue.username,
            correo: formValue.correo,
            contacto: formValue.contacto,
            roleId: Number(formValue.role),
            // Handle password: send only if provided or if it's a new user
            password: formValue.password || this.ownerData?.password || ''
        };

        const request$ = this.ownerData?.idOwner
            ? this._apiService.updateOwner(ownerPayload)
            : this._apiService.addOwner(ownerPayload);

        request$.subscribe({
            next: () => {
                this.onSave.emit();
            },
            error: (err) => {
                console.error('Error saving owner', err);
                // Handle error feedback here (optional)
            }
        });
    }

    hasError(field: string, errorType: string): boolean {
        const control = this.ownerForm.get(field);
        return !!(control?.hasError(errorType) && control?.touched);
    }
}
