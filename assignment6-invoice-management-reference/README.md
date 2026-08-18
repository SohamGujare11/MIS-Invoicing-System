# Invoice Management Module – Assignment Reference

This reference is based on the Invoice Management end-user documentation supplied for the assignment.

## Module
Invoice Management

## Application flow
1. Open Manage Estimate.
2. Select the required estimate.
3. Open Generate Invoice.
4. Enter customer Email ID.
5. Enter Amount Paid.
6. Click Generate Invoice.
7. The invoice is created.
8. The invoice PDF is generated and downloaded.
9. The generated invoice PDF is sent to the entered email address.

## Estimate information used by Invoice
- Estimate ID
- Chain ID
- Company Name
- Service
- Quantity
- Cost per Quantity
- Amount Payable
- Delivery Date
- Delivery Details

## Invoice capabilities documented
- Generate invoice from an existing estimate
- Calculate remaining balance
- Generate and download invoice PDF
- Send invoice PDF by email
- View existing invoices
- Search invoices by Invoice Number, Estimate ID, Chain ID and Company Name
- Edit invoice information where applicable
- Delete invoices where applicable

## Validation
- Estimate information must be available.
- Email ID is required.
- Amount Paid cannot be negative.
- Amount Paid cannot be greater than Amount Payable.

## Application
Live application: https://zippy-puppy-d1a517.netlify.app/
