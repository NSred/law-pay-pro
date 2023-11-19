import {Directive, ElementRef, HostListener} from '@angular/core';

@Directive({
  selector: '[appSecurityCode]',
  standalone: true
})
export class SecurityCodeDirective {

  constructor(private el: ElementRef<HTMLInputElement>) {}

  @HostListener('input', ['$event']) onInputChange(event: Event) {
    const input = this.el.nativeElement;
    let value = input.value.replace(/[^0-9]/g, ''); // Only allow numbers

    // Restrict to 3 digits
    if (value.length > 3) {
      value = value.substring(0, 3);
    }

    input.value = value; // Update the input field
    event.stopPropagation();
  }

}
