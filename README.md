# MCAProcessor
Extract properties from MCA Formatted File (contain several NBTs inside)

# Features
- Filter NBT Properties contained inside MCA File
- Convert NBT Properties to another format
- Extensible design to further process NBT
- Internally convert NBT to JSON

# Implemented Features
- Scan for books from the entire world and output it into a JSON format
- Implemented NBT Library <Source: github.com/Quertz/NBT>

##### Tested on Ubuntu 20.04 LTS

# How to run?
Build using Gradle. 
> gradle run

# Notes
### MCAAdapter
Designed to be able to easily swap MCA Library.
Just Implements the interface.

### NBTAdapter
Designed to be able to easily swap NBT Library.
Just Implements the interface.

## Hope this is useful for you internet! star this project if you think it is useful and thank you for the contribution.
