import {Component, forwardRef, Input} from '@angular/core';
import { CommonModule } from '@angular/common';
import {ControlValueAccessor, FormControl, NG_VALUE_ACCESSOR} from "@angular/forms";
import {InputErrorComponent} from "../input-error/input-error.component";
import {InputError} from "../../../model/error.model";

@Component({
  selector: 'app-input-field',
  standalone: true,
  imports: [CommonModule, InputErrorComponent],
  templateUrl: './input-field.component.html',
  styleUrl: './input-field.component.scss',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => InputFieldComponent),
      multi: true,
    },
  ],
})
export class InputFieldComponent implements ControlValueAccessor {
  @Input() control: FormControl = new FormControl();
  @Input() label: string = '';
  @Input() placeholder: string = '';
  @Input() isDisabled: boolean = false;
  @Input() type: string = 'text';
  private onChange: (value: any) => void = undefined!;
  private onTouched: () => void = undefined!;
  @Input() errors: InputError = undefined!;

  registerOnChange(fn: any): void {
    this.onChange = fn;
    this.control.valueChanges.subscribe(fn);
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  writeValue(obj: any): void {
    this.control.setValue(obj);
  }
  setDisabledState(isDisabled: boolean): void {
    isDisabled ? this.control.disable() : this.control.enable();
  }

}
