To-Dos

*** Bugs ***

- [x] Adobe Textures lost
- [x] Casting Mud lost texture
- [x] lang file missing entries

*** Current Feature ***
- [x] BlockBreaker Support for copper dust and tin dust
- [x] picking up raw bronze crucible returns some other shit
- [x] Tool breaking should return tool head

*** Feature Musket ***
- [ ] Create powder charge item (copper, charcoal, gunpowder)
- [ ] Create musket round item (lead or iron nugget)
- [ ] Create wadding item (paper)
- [ ] Create soaked slow match item (cordage, bucket of water, gunpowder)
- [ ] Create dried slow match (soaked wick on drying rack)
- [ ] Create lit slow match (dried slow match and firebow/torch/flint&steel)
- [ ] Create barrel item
- [ ] Create stock item
- [ ] Create lock assembly item

*** Backlog ***
- [ ] Move Ingot break into chunks logic out of the block and into the ForgeHammer
- [ ] Add Iron Ring Recipe
- [ ] Add chainmail recipe
- [ ] Add Iron Shield
- [ ] Gold Smelting
- [ ] Create a method which adds the types of NBT upgrades and creates a unique float instead of using magic numbers
- [ ] Add forgehammer to oreDict
- [ ] WeaponHead recipes


*** Tid-Bits ***
sed -i -- 's/iron/steel/g' *
rm *.json--
rename s/iron/steel/ iron*

*** Completed ***
- [x] Casting Table
  - [x] Block
  - [x] Gui
  - [x] Crafting recipes
- [x] StoneTongs for all tool heads
  - [x] ItemNBT
  - [x] ItemRender
  - [x] JSON
- [x] Change how bloomery does recipe cookTime
- [x] Duplication Bug for Tool Repair
- [x] Fix Breaker Render to rotate correctly
- [x] Make All Accompanying Recipes
- [x] Update blockstates with:
  - [x] cleanIron
  - [x] steel
  - [x] wootz
- [x] Make an Iron Anvil
  - [x] Check Iron Anvil creation logic
- [x] Make New Ore Tool, Tool Part, Ingot, and Chunk Assets
- [x] Make Steel Recipes and Tools
- [x] Make Wootz Recipes and Tools
- [x] Update tool parts and tool textures
- [x] Light Forge and Firebox with Firebow or Torch
- [x] Any gallagher can be used to make a stone anvil
- [x] Update Crafting for tools to include leatherStrap
- [x] Forge Setting Player on fire but not taking damage
- [x] Forge and Bloomery make Smoke
- [x] Remove cool ingots from inventories with Hand
- [x] Remove cool tools heads from inventories with Hand
- [x] Add rendering in anvil renderer for Cool Ingots
- [x] Hot Ingots should cool down
- [x] Coal is not being removed from the forge or firebox

- [x] Tool Disassembly
- [x] Tool Repair
- [x] Custom Tool Material
- [x] Can't remove fuel from Bloomery
- [x] Crafting Recipe for Iron Ingot to Iron Ball
- [x] Block To Read Temperature
- [x] Bellows Moves Faster
- [x] Create the hoe Item
- [x] Finish Toolhead Recipes
- [x] Add randomInt to anvil
- [x] Create the axe Item
- [x] Create the shovel Item
- [x] Create the axe Model
- [x] Create the shovel model
- [x] Create the hoe model
- [x] Fix Flame creator from Forge and Bloomery
- [x] Copy Heat Handler for Forge to Bloomery
- [x] Create getter and setter for itemNBT
- [x] Create all the textures for all the NBT types
- [x] Check for if the pickaxe head is hot before removing from forge
- [x] fix rotation rendering for Anvil
- [x] Fix renderings in the Anvil
- [x] Fix Renderings in the Forge
- [x] Fix StoneTongs rendering for NBT 8
- [x] Allow redstone, lapis, and shards in the anvil

- [x] Create toolHead Item
  - [x] Give toolHead item NBT
  - [x] Create pickaxehead Item
    - [x] Create pickaxehead SubNBTs
- [x] Create Forging Recipe for toolHeads
- [x] Create Anvil recipe for toolHeads

- [x] Create Slot for Forge to accept toolheads in slot 4

- [x] Stone Anvil
  - [x] Stone Anvil Inventory
  - [x] Stone Anvil Tile
  - [x] Stone Anvil Crafting
  - [x] TESR Inventory Rendering for Anvil

- [x] Iron Chunk Item Model
- [x] Anvil Item Model

- [x] StoneTongs Iron Chunks

- [x] Hammer Crafting
- [x] Iron Chunking

- [x] Forge Recipe Handler
- [x] Hot Iron Ingot Recipe

- [x] Block Drop for failed crucible
- [x] oreDictionary ingotBall to ingotIron
- [x] Tile Firebox Heat Manager
- [x] Fix Bloomery Lit Texture
- [x] Bellows break texture
- [x] Bellows Pump Speed
- [x] Break IronCrucible gives back Ore
- [x] Tile Bloomery Heat Manager
- [x] Tongs Model for hotIron
- [x] Tongs Model for hotIronCooked
- [x] Tongs Model for hotIronBurnt
- [x] Tongs Extract from bloomery for Hot Iron
- [x] Tongs Extract from bloomery for Hot Iron Finished
- [x] Crafting Recipes


         0 | Default StoneTongs
         1 | Empty Crucible Hot
         2 | Empty Crucible Cracked Hot

        ================================

         3 | Hot Iron Crucible
         4 | Hot Cooked Iron Crucible
         5 | Hot Failed Iron Crucible
         6 | Hot Iron Ingot
         7 | Hot Iron Chunk
         --------------------------------
         8 | Hot Iron Pickaxe Head
         9 | Hot Iron Axe Head
        10 | Hot Iron Shovel Head
        11 | Hot Iron Hoe Head

        ================================

        12 | Hot Clean Iron Crucible
        13 | Hot Cooked Clean Iron Crucible
        14 | Hot Failed Clean Iron Crucible
        15 | Hot Clean Iron Ball
        16 | Hot Clean Iron Chunk
        --------------------------------
        17 | Hot Clean Iron Pickaxe Head
        18 | Hot Clean Iron Axe Head
        19 | Hot Clean Iron Shovel Head
        20 | Hot Clean Iron Hoe Head

        ================================

        21 | Hot Steel Crucible
        22 | Hot Cooked Steel Crucible
        23 | Hot Failed Steel Crucible
        24 | Hot Steel Ingot
        25 | Hot Steel Chunk
        --------------------------------
        26 | Hot Steel Pickaxe Head
        27 | Hot Steel Axe Head
        28 | Hot Steel Shovel Head
        29 | Hot Steel Hoe Head