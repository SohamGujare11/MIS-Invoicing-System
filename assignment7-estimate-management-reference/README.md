# Estimate Management Module – Assignment Reference

This folder contains the Estimate Management implementation/reference for the Estimate Management assignment.

## Module
Estimate Management

## Main functionality
- Add Estimate
- View / Manage Estimates
- Edit Estimate
- Delete Estimate
- Calculate total estimated amount from quantity × cost per unit
- Select Group → Chain → Brand → Zone
- Validate service, quantity, cost and delivery date
- REST API for estimate CRUD operations
- Filter estimates by Chain, Group, Brand and Zone

## Estimate fields
- Estimate ID
- Chain
- Group Name
- Brand Name
- Zone Name
- Service
- Quantity
- Cost Per Unit
- Total Cost
- Delivery Date
- Delivery Details
- Created At
- Updated At

## Reference source
The Estimate implementation was reviewed from the authorized reference repository:
https://github.com/Atharvx12/Group-Management-System

Relevant reference files include:
- `AddEstimate.js`
- `EditEstimate.js`
- `ManageEstimate.js`
- `estimateService.js`
- `Estimate.java`
- `EstimateController.java`
- `EstimateRepository.java`
- `EstimateService.java`
- `EstimateServiceImpl.java`

## API endpoints
- GET `/estimates`
- GET `/estimates/{id}`
- POST `/estimates`
- PUT `/estimates/{id}`
- DELETE `/estimates/{id}`
- GET `/estimates/chain/{chainId}`
- GET `/estimates/group/{groupName}`
- GET `/estimates/brand/{brandName}`
- GET `/estimates/zone/{zoneName}`
