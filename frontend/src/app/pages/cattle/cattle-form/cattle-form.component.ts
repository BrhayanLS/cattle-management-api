import { Component, EventEmitter, Input, OnInit, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../../../services/api.service';
import { ICattle, IAllCattle } from '../../../models/cattle.model';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-cattle-form',
    standalone: true,
    imports: [ReactiveFormsModule, CommonModule],
    template: `
    <form [formGroup]="cattleForm" class="row g-3 needs-Validation" novalidate (submit)="enviar($event)">
      <div class="mb-3">
        <label for="nombre" class="form-label">Nombre</label>
        <input type="text" class="form-control" id="nombre" formControlName="nombre"
        [ngClass]="{'is-invalid' : hasErrors('nombre', 'required') || hasErrors('nombre','minlength')}">
        @if (hasErrors('nombre', 'required')) { <span class="text-danger">Complete el campo</span> }
        @if (hasErrors('nombre', 'minlength')) { <span class="text-danger">El nombre debe tener al menos 3 caracteres</span> }
      </div>
      <div class="mb-3">
        <label for="fechaNacimiento" class="form-label">Fecha de nacimiento</label>
        <input type="date" class="form-control" id="fechaNacimiento" formControlName="fechaNacimiento"
        [ngClass]="{'is-invalid' : hasErrors('fechaNacimiento', 'required') || hasErrors('fechaNacimiento','minlength')}">
        @if (hasErrors('fechaNacimiento', 'required')) { <span class="text-danger">Complete el campo</span> }
        @if (hasErrors('fechaNacimiento', 'minlength')) { <span class="text-danger">La fecha debe tener al menos 10 caracteres</span> }
      </div>
      <div class="mb-3">
        <label for="idOwner" class="form-label">Id Dueño</label>
        <input type="number" class="form-control" id="idOwner" formControlName="idOwner"
        [ngClass]="{'is-invalid' : hasErrors('idOwner', 'required') || hasErrors('idOwner','minlength')}">
        @if (hasErrors('idOwner', 'required')) { <span class="text-danger">Complete el campo</span> }
        @if (hasErrors('idOwner', 'minlength')) { <span class="text-danger">El id debe tener al menos 1 caracteres</span> }
      </div>
      <div class="col-12 text-end">
        <button class="btn btn-secondary me-2" type="button" (click)="cancel()">Cancelar</button>
        <button class="btn btn-primary" type="submit">Guardar</button>
      </div>
    </form>
  `
})
export class CattleFormComponent implements OnInit {
    @Input() idCattle: number | null = null;
    @Output() onSave = new EventEmitter<void>();
    @Output() onCancel = new EventEmitter<void>();

    private _apiService = inject(ApiService);
    private formBuilder = inject(FormBuilder);

    cattleForm: FormGroup = this.formBuilder.group({
        nombre: ['', [Validators.required, Validators.minLength(3)]],
        fechaNacimiento: ['', [Validators.required, Validators.minLength(10)]],
        idOwner: ['', [Validators.required, Validators.minLength(1)]]
    });

    cattleUpdate: IAllCattle = {
        idCattle: 0,
        idOwner: 0,
        apellido: '',
        correo: '',
        contacto: '',
        nombre: '',
        estado: 0,
        fechaNacimiento: new Date(),
        nombreOwner: '',
    };

    ngOnInit(): void {
        if (this.idCattle) {
            this.loadCattle(this.idCattle);
        }
    }

    loadCattle(id: number): void {
        this._apiService.getCattle(id).subscribe((data: any) => {
            this.cattleUpdate = data;
            this.cattleForm.patchValue(this.cattleUpdate);
        });
    }

    enviar(event: Event) {
        event.preventDefault();

        if (this.cattleForm.invalid) {
            this.cattleForm.markAllAsTouched();
            return;
        }

        const cattle: ICattle = {
            idCattle: this.idCattle || undefined,
            nombre: this.cattleForm.value.nombre,
            fechaNacimiento: this.cattleForm.value.fechaNacimiento,
            idOwner: this.cattleForm.value.idOwner
        };

        this._apiService.updateCattle(cattle).subscribe({
            next: () => {
                this.onSave.emit();
            },
            error: (error) => console.error("Error al guardar", error)
        });
    }

    hasErrors(field: string, typeError: string) {
        return this.cattleForm.get(field)?.hasError(typeError) && this.cattleForm.get(field)?.touched;
    }

    cancel() {
        this.onCancel.emit();
    }
}
