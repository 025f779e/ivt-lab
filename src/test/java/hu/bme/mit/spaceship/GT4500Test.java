package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary_mock;
  private TorpedoStore secondary_mock;

  @BeforeEach
  public void init(){
    primary_mock = mock(TorpedoStore.class);
    secondary_mock = mock(TorpedoStore.class);
    this.ship = new GT4500(primary_mock, secondary_mock);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary_mock.isEmpty()).thenReturn(false);
    when(primary_mock.fire(1)).thenReturn(true);
    when(secondary_mock.isEmpty()).thenReturn(false);
    when(secondary_mock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary_mock, times(1)).isEmpty();
    verify(secondary_mock, times(0)).isEmpty();
    verify(primary_mock, times(1)).fire(1);
    verify(secondary_mock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary_mock.isEmpty()).thenReturn(false);
    when(primary_mock.fire(1)).thenReturn(true);
    when(secondary_mock.isEmpty()).thenReturn(false);
    when(secondary_mock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primary_mock, times(1)).fire(1);
    verify(secondary_mock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Alternate(){
    // Arrange
    when(primary_mock.isEmpty()).thenReturn(false);
    when(primary_mock.fire(1)).thenReturn(true);
    when(secondary_mock.isEmpty()).thenReturn(false);
    when(secondary_mock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
    verify(primary_mock, times(1)).fire(1);
    verify(secondary_mock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_AlternateEmpty(){
    // Arrange
    when(primary_mock.isEmpty()).thenReturn(false);
    when(primary_mock.fire(1)).thenReturn(true);
    when(secondary_mock.isEmpty()).thenReturn(true);
    when(secondary_mock.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
    verify(primary_mock, times(2)).isEmpty();
    verify(secondary_mock, times(1)).isEmpty();
    verify(primary_mock, times(2)).fire(1);
    verify(secondary_mock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Fail(){
    // Arrange
    when(primary_mock.isEmpty()).thenReturn(false);
    when(primary_mock.fire(1)).thenReturn(false);
    when(secondary_mock.isEmpty()).thenReturn(false);
    when(secondary_mock.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(primary_mock, times(1)).isEmpty();
    verify(secondary_mock, times(0)).isEmpty();
    verify(primary_mock, times(1)).fire(1);
    verify(secondary_mock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Alternate2(){
    // Arrange
    when(primary_mock.isEmpty()).thenReturn(false);
    when(primary_mock.fire(1)).thenReturn(true);
    when(secondary_mock.isEmpty()).thenReturn(false);
    when(secondary_mock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result3 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
    assertEquals(true, result3);
    verify(primary_mock, times(2)).fire(1);
    verify(secondary_mock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_AllFailOne(){
    // Arrange
    when(primary_mock.fire(1)).thenReturn(false);
    when(secondary_mock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primary_mock, times(1)).fire(1);
    verify(secondary_mock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_AllFailOther(){
    // Arrange
    when(primary_mock.fire(1)).thenReturn(true);
    when(secondary_mock.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primary_mock, times(1)).fire(1);
    verify(secondary_mock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_AllFailBoth(){
    // Arrange
    when(primary_mock.fire(1)).thenReturn(false);
    when(secondary_mock.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(primary_mock, times(1)).fire(1);
    verify(secondary_mock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Empty(){
    // Arrange
    when(primary_mock.isEmpty()).thenReturn(true);
    when(primary_mock.fire(1)).thenReturn(false);
    when(secondary_mock.isEmpty()).thenReturn(false);
    when(secondary_mock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary_mock, times(1)).isEmpty();
    verify(secondary_mock, times(1)).isEmpty();
    verify(primary_mock, times(0)).fire(1);
    verify(secondary_mock, times(1)).fire(1);
  }

}
