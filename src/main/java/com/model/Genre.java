/**
 * @author Tre
 */

package com.model;

/**
 * The {@code Genre} enum represents various musical genres that can be 
 * assigned to songs in the application.
 * 
 * <p> The available genres are:
 * <ul>
 *   <li>{@link #ROCK} - A genre characterized by a strong rhythm and often 
 *   featuring electric guitar and drums.</li>
 *   <li>{@link #POP} - Popular music with catchy melodies and wide appeal.</li>
 *   <li>{@link #COUNTRY} - Music originating from rural southern U.S. with 
 *   storytelling lyrics and traditional instruments like the banjo.</li>
 *   <li>{@link #HIPHOP} - A genre known for rhythmic speech (rap) and 
 *   electronic backing tracks.</li>
 *   <li>{@link #ELECTRONIC} - Music produced using electronic instruments, 
 *   including synthesizers and drum machines.</li>
 *   <li>{@link #JAZZ} - A genre characterized by improvisation, complex 
 *   harmonies, and a strong rhythmic groove.</li>
 *   <li>{@link #CLASSICAL} - Traditional Western music, often orchestral 
 *   and composed in a formal structure.</li>
 *   <li>{@link #SOUL} - Music with roots in African-American gospel, rhythm, 
 *   and blues, known for its emotional expression.</li>
 *   <li>{@link #INDIE} - Independent music produced without major commercial 
 *   labels, often featuring experimental styles.</li>
 *   <li>{@link #FOLK} - Traditional and culturally rooted music, often 
 *   focusing on storytelling through song.</li>
 * </ul>
 */
public enum Genre {

    /** Represents the rock genre. */
    ROCK,

    /** Represents the pop genre. */
    POP, 

    /** Represents the country genre. */
    COUNTRY, 

    /** Represents the hip-hop genre. */
    HIPHOP,

    /** Represents the electronic music genre. */
    ELECTRONIC,

    /** Represents the jazz genre. */
    JAZZ,

    /** Represents the classical music genre. */
    CLASSICAL,

    /** Represents the soul music genre. */
    SOUL,

    /** Represents the indie genre. */
    INDIE,

    /** Represents the folk genre. */
    FOLK
}
