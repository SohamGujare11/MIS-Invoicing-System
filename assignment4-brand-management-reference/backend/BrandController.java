package com.itvedant.groupmanagement.controller;

import com.itvedant.groupmanagement.entity.Brand;
import com.itvedant.groupmanagement.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/brands")
@CrossOrigin(origins = "http://localhost:3000")
public class BrandController {
    private final BrandService brandService;
    public BrandController(BrandService brandService){this.brandService=brandService;}
    @GetMapping public ResponseEntity<List<Brand>> getAllBrands(){return ResponseEntity.ok(brandService.getAllBrands());}
    @GetMapping("/{id}") public ResponseEntity<Brand> getBrandById(@PathVariable Integer id){return ResponseEntity.ok(brandService.getBrandById(id));}
    @PostMapping public ResponseEntity<Brand> addBrand(@RequestBody Brand brand){return ResponseEntity.ok(brandService.addBrand(brand));}
    @PutMapping("/{id}") public ResponseEntity<Brand> updateBrand(@PathVariable Integer id,@RequestBody Brand brand){return ResponseEntity.ok(brandService.updateBrand(id,brand));}
    @DeleteMapping("/{id}") public ResponseEntity<String> deleteBrand(@PathVariable Integer id){brandService.deleteBrand(id);return ResponseEntity.ok("Brand deleted successfully");}
    @GetMapping("/chain/{chainId}") public ResponseEntity<List<Brand>> getBrandsByChain(@PathVariable Integer chainId){return ResponseEntity.ok(brandService.getBrandsByChain(chainId));}
    @GetMapping("/group/{groupId}") public ResponseEntity<List<Brand>> getBrandsByGroup(@PathVariable Integer groupId){return ResponseEntity.ok(brandService.getBrandsByGroup(groupId));}
}
