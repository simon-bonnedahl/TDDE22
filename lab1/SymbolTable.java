package TDDE22.lab1;

//TODO: Komplettering. Storleken på er hashtabell fungerar inte som
//den ska vid större tabllstorlekar.

/**
 * This class represents a symbol table, or hash table, with a very simple hash
 * function. Observe that you are not supposed to change hash function. Ensure
 * that you use linear probing as your method of collision handling.
 *
 * @author Magnus Nielsen, Tommy Färnqvist ...
 */
public class SymbolTable {
	private static final int INIT_CAPACITY = 7;

	/* Number of key-value pairs in the symbol table */
	private int size;
	/* Size of linear probing table */
	private int maxSize;
	/* The keys */
	private String[] keys;
	/* The values */
	private Character[] vals;

	/**
	 * Create an empty hash table - use 7 as default size
	 */
	public SymbolTable() {
		this(INIT_CAPACITY);
	}

	/**
	 * Create linear probing hash table of given capacity
	 */
	public SymbolTable(int capacity) {
		size = 0;
		maxSize = capacity;
		keys = new String[maxSize];
		vals = new Character[maxSize];
	}

	/**
	 * Return the number of key-value pairs in the symbol table
	 */
	public int size() {
		return size;
	}

	/**
	 * Is the symbol table empty?
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Does a key-value pair with the given key exist in the symbol table?
	 */
	public boolean contains(String key) {
		return get(key) != null;
	}

	/**
	 * Hash function for keys - returns value between 0 and maxSize-1
	 */
	public int hash(String key) {
		int i;
		int v = 0;

		for (i = 0; i < key.length(); i++) {
			v += key.charAt(i);
		}
		return v % maxSize;
	}

	/**
	 * Insert the key-value pair into the symbol table. TODO: implement the put
	 * method.
	 */

	// TODO: Komplettering. Ni bör enbart behöva anropa hashfunktionen
	// en gång per funktion.

	public void put(String key, Character val) {

		if (key.length() == 0) {
			return;
		}
		
		if (val == null) {
			delete(key);
			return;
		}
		
		int startIndex = hash(key);
		int index;

		if (size() <= maxSize) {

			for (int i = startIndex; i < startIndex + maxSize; i++) {
				index = i % maxSize;
				if (keys[index] == null) {
					keys[index] = key;
					vals[index] = val;
					size++;
					break;
				}
				if(keys[index].equals(key)) {
					vals[index] = val;
					break;
				}

			}

		}

		// TODO: Komplettering. Vad händer om nyckeln finns, men ligger
		// senare i kollisionskedjan (gäller både om tabellen är full
		// och om den inte är det)?

		// Kolla så att tabellen inte är
		// full

		// TODO: Kommentar. Det går att lösa detta med en loop utan
		// att ha behandla vissa fall innan loopen. Fundera också kring
		// valet av loop. Det finns en risk att fastna i en evig loop
		// (om tabellen blir full när ni använder er av while), vilket
		// är viktigt att ta hänsyn till när while-loopar används i den
		// här typen av implementationer.

		return;
	}

	/*
	 * while (keys[index] != null && !keys[index].equals(key)) {
	 * 
	 * index = (index+1) % maxSize; }
	 * 
	 * return index;
	 */

	/**
	 * Return the value associated with the given key, null if no such value. TODO:
	 * implement the get method.
	 */
	// TODO: Komplettering. Eftersom det är två anrop till er funktion
	// getKeyIndex kommer hela den funktionen att köras två gånger i
	// värsta fallet. Går detta att undvika? Check!
	public Character get(String key) {
		int startIndex = hash(key);
		int index;

		for (int i = startIndex; i < startIndex + maxSize; i++ ) {
			index = i % maxSize;
			if (keys[index] != null && keys[index].equals(key)) {
				return vals[index];
			}

		}
		return null;

	}

	/**
	 * Delete the key (and associated value) from the symbol table. TODO: implement
	 * the delete method.
	 */
	public void delete(String key) {

		int startIndex = hash(key);
		int index = 0;

		for (int i = startIndex; i < startIndex + maxSize; i++) {
			index = i % maxSize;
			if (keys[index] != null && keys[index].equals(key)) {
				keys[index] = null;
				vals[index] = null;
				size--;
				
			}

		}
		startIndex = (index + 1) % maxSize;
		
		for(int i= startIndex; i<startIndex + maxSize; i++) {
			index = i % maxSize;
			if(keys[index] == null) {
				return;
			}
			String tmpKey = keys[index];
			Character tmpVal = vals[index];
			keys[index] = null;
			vals[index] = null;
			size--;
			put(tmpKey, tmpVal);
			
		
		}
		return;
	}

	/**
	 * Print the contents of the symbol table.
	 */
	public void dump() {
		String str = "";

		for (int i = 0; i < maxSize; i++) {
			str = str + i + ". " + vals[i];
			if (keys[i] != null) {
				str = str + " " + keys[i] + " (";
				str = str + hash(keys[i]) + ")";
			} else {
				str = str + " -";
			}
			System.out.println(str);
			str = "";
		}
	}
}
