# Assignment 4: Ordered Dictionary using a Binary Search Tree

## Overview

This Java project implements an **ordered dictionary** using a **binary search tree (BST)**. The dictionary supports various media types and enables users to query, add, and delete entries through a **text-based user interface**. Each entry is uniquely identified by a `Key` composed of a `label` and `type`.

## Classes Implemented

### `Key.java`
Represents the key used in the dictionary.

- `Key(String theLabel, int theType)` — Constructor; label is stored in lowercase.
- `getLabel()` — Returns the label.
- `getType()` — Returns the type.
- `compareTo(Key k)` — Implements comparison for ordering keys.

### `Record.java`
Stores the value (data) associated with a key.

- `Record(Key k, String theData)` — Constructor.
- `getKey()` — Returns the key.
- `getDataItem()` — Returns the stored data.

### `BSTNode.java`
Represents a node in the BST.

- Standard getters and setters for left, right, parent, and record.
- `isLeaf()` — Checks if node is a leaf.

### `BinarySearchTree.java`
Implements core BST logic.

- `get(BSTNode r, Key k)` — Retrieves a node by key.
- `insert(BSTNode r, Record d)` — Inserts a record.
- `remove(BSTNode r, Key k)` — Removes a record.
- `successor`, `predecessor`, `smallest`, `largest` — Navigational methods.

### `BSTDictionary.java`
Implements `BSTDictionaryADT` interface using `BinarySearchTree`.

- `get`, `put`, `remove`
- `successor`, `predecessor`
- `smallest`, `largest`

### `Interface.java`
Text-based UI with the `main()` method.

- Reads data from an input file.
- Accepts commands like: `define`, `translate`, `sound`, `add`, `delete`, `list`, etc.

## File Format

Each record is specified as:

```
<label>
<type indicator + data>
```

Examples:
- `"flower"` → `"flower.jpg"` (type 6)
- `"course"` → `"http://example.com/course.html"` (type 8)

## Supported Types

| Type | Description        | Indicator | Data format         |
|------|--------------------|-----------|---------------------|
| 1    | Definition         | none      | Plain text          |
| 2    | French translation | `/`       | `/bonjour`          |
| 3    | Sound              | `-`       | `-sound.wav`        |
| 4    | Music              | `+`       | `+music.mid`        |
| 5    | Voice              | `*`       | `*voice.wav`        |
| 6    | Image              | `.jpg`    | `image.jpg`         |
| 7    | Animated image     | `.gif`    | `image.gif`         |
| 8    | Web page           | `.html`   | `site.html`         |

## Commands

- `define w`, `translate w`, `sound w`, `play w`, `say w`, `show w`, `animate w`, `browse w`
- `add w t c`, `delete w t`
- `list prefix`, `first`, `last`
- `exit`

## How to Run

```bash
java Interface input.txt
```

Make sure `input.txt` is correctly formatted.

## Notes

- Media rendering uses: `SoundPlayer.java`, `PictureViewer.java`, `ShowHTML.java`
- Labels are stored in lowercase.
- Handle exceptions for missing files or invalid input.

## Evaluation

- Dictionary: 4 marks
- Interface: 4 marks
- TestDict: 4 marks
- Compilation and output: 2 marks
- Code style: 6 marks

## License

For academic use only. Western University CS 2210a — Data Structures and Algorithms.
