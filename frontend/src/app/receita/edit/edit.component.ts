import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, OnInit, signal, Renderer2, ElementRef } from '@angular/core';
import { Receita } from '@interfaces/receita.interface';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field'
import { FormsModule, ReactiveFormsModule, Validators, FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { CurrencyMaskModule } from "ng2-currency-mask";
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { Utils } from '@commons/utils';

@Component({
  selector: 'app-edit',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatFormFieldModule,
    MatLabel, MatInputModule, MatSelectModule, CurrencyMaskModule, MatDatepickerModule,
    MatIconModule, MatButtonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  form!: FormGroup

  errorMessage = ""
  successMessage = ""

  isSubmitted = false
  isLoading = false

  receita?: Receita;
  action?: string;

  tipos = ['Salario', 'Fatura', 'Benefícios'];
  clientes = ['SIS', 'Belago', 'Stefanini', 'Procics'];

  constructor(private fb: FormBuilder) { }

  ngOnInit() {

    this.form = this.fb.group({
      descricao: ['', Validators.required],
      tipo: ['', Validators.required],
      cliente: ['', Validators.required],
      diaFaturamento: ['', Validators.required],
      valor: ['', Validators.required]
    });

    const state = history.state;
    this.receita = state.receita;
    this.action = state.action;


    if (this.receita) {
      this.form.get('descricao')?.setValue(this.receita.dsReceita);
      this.form.get('tipo')?.setValue(this.receita.tipoReceita.dsTipoReceita);
      this.form.get('cliente')?.setValue(this.receita.fonteReceita.dsFonteReceita);
      this.form.get('valor')?.setValue(this.receita.vlReceita);
    }
  }

  save() {
    this.isSubmitted = true;
    this.form.markAllAsTouched(); // 🔥 Marca todos os campos como "tocados"

    if (this.form.invalid) {
      return;
    }

    Utils.toShowSuccess(this, 'Receita incluída com sucesso!')
  }

  permitirApenasNumeros(event: KeyboardEvent): boolean {
    const charCode = event.key;
    const isNumber = /^[0-9]$/.test(charCode);

    // Impede entrada se não for número
    if (!isNumber) {
      event.preventDefault();
      return false;
    }

    return true;
  }

  descricaoErrorMessage() {
    if (this.form.get('descricao')?.hasError('required')) {
      return 'Descrição é obrigatório';
    }
    return '';
  }

  tipoErrorMessage() {
    if (this.form.get('tipo')?.hasError('required')) {
      return 'Tipo é obrigatório';
    }
    return '';
  }

  clienteErrorMessage() {
    if (this.form.get('cliente')?.hasError('required')) {
      return 'Cliente é obrigatório';
    }
    return '';
  }

  dataErrorMessage() {
    if (this.form.get('diaFaturamento')?.hasError('required')) {
      return 'Dia do faturamento é obrigatório';
    }
    return '';
  }

  valorErrorMessage() {
    if (this.form.get('valor')?.hasError('required') || this.form.get('valor')?.value == 0) {
      return 'Valor é obrigatório';
    }
    return '';
  }

  goBack() {
    window.history.back();
  }
}
