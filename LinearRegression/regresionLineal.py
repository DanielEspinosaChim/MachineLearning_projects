#Importa las librerias necesarias 
from sklearn.linear_model import LinearRegression
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split
#cargar el dataSet
df = pd.read_csv('example_semicolon.csv')
print(df.head(10))
#pasarlo a dataFrame para manipular mejor los datos
df=pd.DataFrame(df)

#cargar las variables de entrada, Features o variables explicativas
X = df[['Seguidores', 'Frecuencia_Publicaciones']].values
#cargar las variables objetivo
y = df[['Interacciones']].values

# Dividir los datos en conjuntos de entrenamiento y prueba
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
#las "x" y "y" son los datos cargados 
#test_size=0.2 indica que el 20% de los datos se usaran de prueba
#random_state=42 es una semilla que garantiza que esto suceda

# Crear y entrenar el modelo de regresión lineal
modelo = LinearRegression()
#entrena el modelo 
modelo.fit(X_train, y_train)

#Realizar predicciones con el conjunto de prueba
y_pred = modelo.predict(X_test)

# Calcular el MSE para el conjunto de entrenamiento
y_train_pred = modelo.predict(X_train)
train_error = mean_squared_error(y_train, y_train_pred)
print(f"Error cuadrático medio en el conjunto de entrenamiento: {train_error}")

# Calcular el MSE para el conjunto de prueba (ya lo tienes en tu código)
error = mean_squared_error(y_test, y_pred)
print(f"Error cuadrático medio en el conjunto de prueba: {error}")

#predice un valor
valor_a_predecir = np.array([[16722, 4]])

# Realizar la predicción
valor_predicho = modelo.predict(valor_a_predecir)
print(f"El valor aproximado del valor con las características proporcionadas es: {valor_predicho[0][0]}")

# Creación de la visualización 3D con el plano de regresión
from mpl_toolkits.mplot3d import Axes3D

# Crear una malla de puntos
x_surf, y_surf = np.meshgrid(np.linspace(df['Seguidores'].min(), df['Seguidores'].max(), 100), 
np.linspace(df['Frecuencia_Publicaciones'].min(), df['Frecuencia_Publicaciones'].max(), 100))
onlyX = np.array([x_surf.ravel(), y_surf.ravel()]).T  # Convertir a array de NumPy

# Realizar la predicción con el array de NumPy
fittedY = modelo.predict(onlyX)

# Crear un gráfico 3D
fig = plt.figure(figsize=(12, 8))
ax = fig.add_subplot(111, projection='3d')

# Gráfico de dispersión con los datos originales
ax.scatter(df['Seguidores'], df['Frecuencia_Publicaciones'], df['Interacciones'], color='red', alpha=0.6, edgecolors='w', s=30)

# Gráfico del plano de regresión
ax.plot_surface(x_surf, y_surf, fittedY.reshape(x_surf.shape), color='m', alpha=0.3)

# Etiquetas y título
ax.set_xlabel('Seguidores')
ax.set_ylabel('Frecuencia de Publicaciones')
ax.set_zlabel('Interacciones')
ax.set_title('Relación 3D y Plano de Regresión Lineal')
plt.show()