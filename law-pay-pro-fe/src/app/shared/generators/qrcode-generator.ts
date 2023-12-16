import * as QRCode from 'qrcode';
export function generateQRCode(base64String: string): void {
  const canvas = document.getElementById('canvas') as HTMLCanvasElement;

  QRCode.toCanvas(canvas, base64String, function (error) {
    if (error) console.error(error);
    console.log('Success!');
  });
}
