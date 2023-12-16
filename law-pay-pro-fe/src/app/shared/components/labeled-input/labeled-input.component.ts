import {Component, Input} from '@angular/core';
import { CommonModule } from '@angular/common';
import {InputErrorComponent} from "../inputs/input-error/input-error.component";
import {ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-labeled-input',
  standalone: true,
    imports: [CommonModule, InputErrorComponent, ReactiveFormsModule],
  templateUrl: './labeled-input.component.html',
  styleUrl: './labeled-input.component.scss'
})
export class LabeledInputComponent {
  @Input() label: string = '';
  @Input() value: string = '';

}
