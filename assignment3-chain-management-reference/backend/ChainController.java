@RestController
@RequestMapping("/chains")
@CrossOrigin(origins = "http://localhost:3000")
public class ChainController {
    private final ChainService chainService;
    public ChainController(ChainService chainService){this.chainService=chainService;}
    @GetMapping public ResponseEntity<List<Chain>> getAllChains(){return ResponseEntity.ok(chainService.getAllChains());}
    @GetMapping("/group/{groupId}") public ResponseEntity<List<Chain>> getChainsByGroup(@PathVariable Integer groupId){return ResponseEntity.ok(chainService.getChainsByGroup(groupId));}
    @GetMapping("/{id}") public ResponseEntity<Chain> getChainById(@PathVariable Integer id){return ResponseEntity.ok(chainService.getChainById(id));}
    @PostMapping public ResponseEntity<Chain> addChain(@RequestBody Chain chain){return ResponseEntity.ok(chainService.addChain(chain));}
    @PutMapping("/{id}") public ResponseEntity<Chain> updateChain(@PathVariable Integer id,@RequestBody Chain chain){return ResponseEntity.ok(chainService.updateChain(id,chain));}
    @DeleteMapping("/{id}") public ResponseEntity<String> deleteChain(@PathVariable Integer id){chainService.deleteChain(id);return ResponseEntity.ok("Chain deleted successfully");}
}